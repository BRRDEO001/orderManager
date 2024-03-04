package controllers;

import lombok.Getter;
import lombok.Setter;
import models.MenuItem;
import org.ordermanager.MenuItemViewModel;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;


@Getter
@Setter
public class ItemController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;

    @Wire
    private Window modalDialog;

    @Wire
    private Div sizeCosts;

    @Wire("#itemName")
    private Textbox itemName;

    @Wire("#itemDescription")
    private Textbox itemDescription;

    @Wire
    private MenuItemViewModel menuItemViewModel;

    private int sizeCostCounter = 0;

    public ItemController() {
        menuItemViewModel = new MenuItemViewModel();
    }

    @Listen("onClick = #closeBtn")
    public void showModal(Event e) {
        modalDialog.detach();
    }

    @Listen("onClick = #addSizeBtn")
    public void addSizeRow() {
        sizeCostCounter++;
        String sizeId = "sizeInput" + sizeCostCounter + System.currentTimeMillis();
        String costId = "costInput" + sizeCostCounter + System.currentTimeMillis();

        // Create size input
        Textbox sizeInput = new Textbox();
        sizeInput.setId(sizeId);
        sizeInput.setPlaceholder("Size");
        sizeInput.setStyle("margin:5px;");

        // Create cost input
        Textbox costInput = new Textbox();
        costInput.setId(costId);
        costInput.setPlaceholder("Cost");
        costInput.setStyle("margin:5px;");

        // Create a new div to contain the size and cost inputs
        Div row = new Div();
        row.appendChild(sizeInput);
        row.appendChild(costInput);
        row.setStyle("margin:2px;");

        // Add size and cost inputs to the new div
        sizeCosts.appendChild(row);
    }

    @Listen("onClick = #saveBtn")
    @NotifyChange("filteredMenuList")
    public void saveItem() {
        if (itemName.getValue().isEmpty() || itemDescription.getValue().isEmpty()) {
            // Display an error message or handle validation
            return;
        }

        // Create a new MenuItem
        MenuItem newItem = new MenuItem(itemName.getValue(), itemDescription.getValue(), null);
        menuItemViewModel.getMenuList().add(newItem);

        // Update the filteredMenuList to reflect the changes
        menuItemViewModel.setFilteredMenuList(new ArrayList<>(menuItemViewModel.getMenuList()));

        // Clear the input fields
        itemName.setValue("");
        itemDescription.setValue("");

        // Notify ZK bindings about the changes
        BindUtils.postNotifyChange(null, null, menuItemViewModel, "filteredMenuList");

        modalDialog.detach();
    }
}
