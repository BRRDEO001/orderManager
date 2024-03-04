package org.ordermanager;

import controllers.ModalDialogController;
import lombok.Getter;
import lombok.Setter;
import models.MenuItem;
import models.SizeCost;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class MenuItemViewModel {

    private List<MenuItem> menuList;


    private List<MenuItem> filteredMenuList;

    private MenuItem selectedItem;
    private String selectedWeek;
    private String searchText;

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

        filteredMenuList = new ArrayList<>(menuList);
    }

    private List<SizeCost> createDummySizes() {
        List<SizeCost> sizes = new ArrayList<>();
        sizes.add(new SizeCost("Small", 5.99));
        sizes.add(new SizeCost("Medium", 7.99));
        sizes.add(new SizeCost("Large", 9.99));
        return sizes;
    }

    @Command
    public void selectItem(@BindingParam("item") MenuItem item) {
        if (item != null) {
            setSelectedItem(item);
            BindUtils.postNotifyChange(null, null, this, "selectedItem");
        }
    }

    @Command
    @NotifyChange({"selectedItem", "menuList"})
    public void updateItem(@BindingParam("item") MenuItem item){
        if (item != null && selectedItem != null) {
            selectedItem.setItemName(item.getItemName());
            selectedItem.setItemDescription(item.getItemDescription());
            selectedItem.setSizes(item.getSizes());
            BindUtils.postNotifyChange(null, null, this, "selectedItem");
            BindUtils.postNotifyChange(null, null, this, "menuList");
        }
    }

    @Command
    @NotifyChange("filteredMenuList")
    public void searchItems(@BindingParam("itemStr") String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            filteredMenuList = menuList.stream()
                    .filter(menuItem -> menuItem.getItemName().contains(searchText))
                    .collect(Collectors.toList());
        } else {
            filteredMenuList = new ArrayList<>(menuList);
        }
    }
    @Command
    public void showModal() {
        ModalDialogController modalDialogController = new ModalDialogController();
        modalDialogController.showModal();
    }


}

