package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public class MenuItem {
    String itemName;
    String itemDescription;
    HashMap<String, Double > sizes;

}
