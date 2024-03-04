package controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class ModalDialogController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;

    @Listen("onClick = #addItem")
    public void showModal() {

        Window window = (Window)Executions.createComponents(
                "/createItem.zul", null, null);
        window.doModal();
    }
}
