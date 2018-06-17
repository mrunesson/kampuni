package org.linuxalert.kampuni;

import com.google.common.annotations.VisibleForTesting;
import org.linuxalert.kampuni.model.Assortment;
import org.linuxalert.kampuni.model.Item;
import org.linuxalert.kampuni.model.Result;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AssortmentService {

  private static final System.Logger LOGGER = System.getLogger(AssortmentService.class.getName());
  private static AssortmentService instance;
  private static ScheduledExecutorService scheduler;


  private Collection<Item> items = List.of();
  private Map<String,Set<Item>> idIndex = Map.of();
  private LocalDateTime lastUpdate;
  private LocalDateTime lastCheck;
  private AssortmentAdapter assortmentAdapter;

  public static synchronized AssortmentService getInstance() {
    if (Objects.isNull(instance)) {
      instance = new AssortmentService();
      instance.updateCache();
      scheduler = Executors.newScheduledThreadPool(1);
      // TODO: Base initial delay on lastUpdate
      scheduler.scheduleWithFixedDelay(() -> instance.updateCache(),
          1, 1, TimeUnit.DAYS );
    }
    return instance;
  }

  public boolean ready() {
    return lastCheck != null;
  }

  public boolean health() {
    return lastCheck.isAfter(lastCheck.minusHours(25));
  }

  private AssortmentService() {
    assortmentAdapter = new AssortmentAdapter();
  }

  @VisibleForTesting
  private AssortmentService(InputStream assortmentStream) {
    assortmentAdapter = new AssortmentAdapter(assortmentStream);
  }


  private void updateCache() {
    Assortment freshData = null;
    LOGGER.log(System.Logger.Level.DEBUG, "Running update of cache from supplier.");
    try {
      freshData = assortmentAdapter.getAssortment();
    } catch (DataReadException e) {
      LOGGER.log(System.Logger.Level.WARNING, "Failed to fetch data from supplier.");
      return;
    }
    lastUpdate = freshData.getSourceCreateTime();
    items = freshData.getItems();

    Map<String,Set<Item>> freshIdIndex = new HashMap<>();
    items.stream().forEach((i) -> {
      if (freshIdIndex.containsKey(i.getProductId())) {
        Set<Item> value = freshIdIndex.get(i.getProductId());
        value.add(i);
      } else {
        Set<Item> value = new HashSet<>();
        value.add(i);
        freshIdIndex.put(i.getProductId(), value);
      }
    });
    idIndex = freshIdIndex;
    lastCheck = LocalDateTime.now();
    LOGGER.log(System.Logger.Level.DEBUG, "Cache is updated.");
  }

  public Result getAll() {
    return new Result(items, lastUpdate);
  }

  public Result getId(String id) {
    return new Result(idIndex.getOrDefault(id, Set.of()), lastUpdate);
  }
}
