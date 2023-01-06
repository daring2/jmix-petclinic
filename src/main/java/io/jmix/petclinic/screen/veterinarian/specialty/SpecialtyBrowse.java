package io.jmix.petclinic.screen.veterinarian.specialty;

import com.vaadin.shared.ui.dnd.DragSourceState;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.TreeGridDropTarget;
import io.jmix.petclinic.entity.veterinarian.Specialty;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.TreeDataGrid;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UiController("petclinic_Specialty.browse")
@UiDescriptor("specialty-browse.xml")
@LookupComponent("table")
@Route(value = "specialities")
public class SpecialtyBrowse extends MasterDetailScreen<Specialty> {

    @Autowired
    private DataGrid<Specialty> table;
    @Autowired
    private TreeDataGrid<Specialty> treeGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        initDragAndDrop();
    }

    private void initDragAndDrop() {
        var vaadinGrid = table.unwrap(com.vaadin.ui.Grid.class);
        var dragSource = new GridDragSource<Specialty>(vaadinGrid);
        dragSource.setEffectAllowed(EffectAllowed.LINK);
        dragSource.setDragDataGenerator(DragSourceState.DATA_TYPE_TEXT_PLAIN, item -> item.getId().toString());
        var vaadinTreeGrid = treeGrid.unwrap(com.vaadin.ui.TreeGrid.class);
        var dropTarget = new TreeGridDropTarget<Specialty>(vaadinTreeGrid, DropMode.ON_TOP);
        dropTarget.addTreeGridDropListener(event -> {
            String dragItem = event.getDataTransferData(DragSourceState.DATA_TYPE_TEXT_PLAIN)
                    .map(UUID::fromString)
                    .map(id -> table.getItems().getItem(id))
                    .map(Specialty::getName)
                    .orElse(null);
            String dropItem = event.getDropTargetRow()
                    .map(Specialty::getName)
                    .orElse(null);
            System.out.println("dragItem=" + dragItem + ", dropItemId=" + dropItem);
        });
    }

}