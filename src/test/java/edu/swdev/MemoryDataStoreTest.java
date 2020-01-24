package edu.swdev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MemoryDataStoreTest {

  private SorData memoryDataStore;

  @BeforeEach
  private void init() {
      this.memoryDataStore = new MemoryDataStore();
      List<SorType> schema = new ArrayList<>();
      schema.add(SorType.FLOAT);
      schema.add(SorType.STRING);
      schema.add(SorType.INT);
      this.memoryDataStore.setSchema(schema);
      List<SorValue> column0 = new ArrayList<>();
      column0.add(new SorValue(6.5f));
      column0.add(new SorValue(7.2f));
      column0.add(new SorValue(-8.76f));
      List<SorValue> column1 = new ArrayList<>();
      column1.add(new SorValue("hi"));
      column1.add(new SorValue("i have space"));
      column1.add(new SorValue("wow"));
      List<SorValue> column2 = new ArrayList<>();
      column2.add(new SorValue(115));
      column2.add(new SorValue(-951));
      column2.add(new SorValue(78));
      List<List<SorValue>> data = new ArrayList<>();
      data.add(column0);
      data.add(column1);
      data.add(column2);
      this.memoryDataStore.setData(data);
  }

  @Test
  public void testGetColType() {
      assertEquals(SorType.FLOAT, this.memoryDataStore.getColType(0));
      assertEquals(SorType.STRING, this.memoryDataStore.getColType(1));
      assertEquals(SorType.INT, this.memoryDataStore.getColType(2));
      assertThrows(IndexOutOfBoundsException.class,
              () -> this.memoryDataStore.getColType(3));
  }

  @Test
  public void getValue() {
      assertEquals(7.2f, this.memoryDataStore.getValue(0, 1).getValue());
      assertEquals("i have space", this.memoryDataStore.getValue(1, 1).getValue());
      assertEquals(78, this.memoryDataStore.getValue(2, 2).getValue());
      assertThrows(IndexOutOfBoundsException.class,
              () -> this.memoryDataStore.getValue(3, 1));
      assertThrows(IndexOutOfBoundsException.class,
              () -> this.memoryDataStore.getValue(1, 5));
  }
}