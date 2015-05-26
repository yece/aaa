package views;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.Table.TableDragMode;

public class Tablesview {
	
    public void configure(Table table, boolean footer, String width, String height,
            boolean stripes, boolean verticalLines,
            boolean horizontalLines, boolean borderless, boolean headers,
            boolean compact, boolean small, boolean rowIndex) {
        table.setSelectable(true);
        table.setMultiSelect(true);
        table.setSortEnabled(true);
        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(true);
        table.setPageLength(6);

        table.setDragMode(TableDragMode.MULTIROW);
        table.setDropHandler(new DropHandler() {
            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            @Override
            public void drop(DragAndDropEvent event) {
                Notification.show(event.getTransferable().toString());
            }
        });
        /*table.setColumnAlignment(DESCRIPTION_PROPERTY, Align.RIGHT);
        table.setColumnAlignment(INDEX_PROPERTY, Align.CENTER);*/

       /* table.setFooterVisible(footer);
        if (footer) {
            table.setColumnFooter(CAPTION_PROPERTY, CAPTION_PROPERTY);
            table.setColumnFooter(DESCRIPTION_PROPERTY, DESCRIPTION_PROPERTY);
            table.setColumnFooter(ICON_PROPERTY, ICON_PROPERTY);
            table.setColumnFooter(INDEX_PROPERTY, INDEX_PROPERTY );
        }*/
        
        table.setWidth(width);
        table.setHeight(height);
            
        /*table.setColumnExpandRatio(CAPTION_PROPERTY,
                expandRatios ? 1.0f : 0);
        table.setColumnExpandRatio(DESCRIPTION_PROPERTY,
                expandRatios ? 1.0f : 0);*/

        if (borderless) {
            table.addStyleName("borderless");
        } else {
            table.removeStyleName("borderless");
        }

        if (!headers) {
            table.addStyleName("no-header");
        } else {
            table.removeStyleName("no-header");
        }

        if (compact) {
            table.addStyleName("compact");
        } else {
            table.removeStyleName("compact");
        }

        if (small) {
            table.addStyleName("small");
        } else {
            table.removeStyleName("small");
        }

        if(rowIndex){
            table.setRowHeaderMode(RowHeaderMode.INDEX);
        }

    }
}
