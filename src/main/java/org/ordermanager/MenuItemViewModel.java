package org.ordermanager;

import lombok.Getter;
import lombok.Setter;
import models.MenuItem;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
public class MenuItemViewModel {

    private List<MenuItem> menuList;
    private MenuItem selectedItem;

    public MenuItemViewModel() {
        menuList = new ArrayList<>();

        MenuItem item1 = new MenuItem("Item 1", "Description 1", createDummySizes());
        MenuItem item2 = new MenuItem("Item 2", "Description 2", createDummySizes());
        MenuItem item3 = new MenuItem("Item 3", "Description 3", createDummySizes());
        MenuItem item4 = new MenuItem("Item 4", "Description 4", createDummySizes());
        MenuItem item5 = new MenuItem("Item 5", "Description 5", createDummySizes());

        menuList.add(item1);
        menuList.add(item2);
        menuList.add(item3);
        menuList.add(item4);
        menuList.add(item5);
    }

    private HashMap<String, Double> createDummySizes() {
        HashMap<String, Double> sizes = new HashMap<>();
        sizes.put("Small", 5.99);
        sizes.put("Medium", 7.99);
        sizes.put("Large", 9.99);
        return sizes;
    }

    @Command
    public void selectItem(@BindingParam("item") MenuItem item) {
        if (item != null) {
            setSelectedItem(item);
            BindUtils.postNotifyChange(null, null, this, "selectedItem");
        }
    }


}
