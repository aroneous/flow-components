package com.vaadin.flow.component.charts;

import static com.vaadin.flow.component.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import com.vaadin.flow.component.charts.events.internal.*;
import org.junit.Test;

import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.Inactive;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsPie;
import com.vaadin.flow.component.charts.model.States;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.style.SolidColor;

/**
 * Tests for the JSON serialization in {@link Configuration}
 *
 */
public class ConfigurationJSONSerializationTest {

    @Test
    public void configurationJSONSerialization_configurationSerializedWithChangeListener_changeListenerNotSerialized() {
        Configuration conf = new Configuration();
        conf.addChangeListener(new ConfigurationChangeListener() {

            @Override
            public void dataAdded(DataAddedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void dataRemoved(DataRemovedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void dataUpdated(DataUpdatedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void itemSliced(ItemSlicedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void seriesStateChanged(SeriesStateEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void seriesAdded(SeriesAddedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void seriesChanged(SeriesChangedEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void axisRescaled(AxisRescaledEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void resetZoom(boolean redraw, boolean animate) {
                // TODO Auto-generated method stub

            }

            @Override
            public void scrollStateChanged(ScrollbarVisibilityChanged event) {

            }
        });
        assertEquals(
                "{\"chart\":{\"styledMode\":false},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_configurationSerializedWithYAxis_yAxisConfigurationNotSerialized() {
        Configuration conf = new Configuration();
        YAxis axis = new YAxis();
        conf.addyAxis(axis);
        assertEquals(
                "{\"chart\":{\"styledMode\":false},\"yAxis\":{\"axisIndex\":0},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_configurationSerializedStyledMode_styledModeSerialized() {
        Configuration conf = new Configuration();
        conf.getChart().setStyledMode(true);
        assertEquals(
                "{\"chart\":{\"styledMode\":true},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_setSeriesAddSeries_noExceptions() {
        Configuration conf = new Configuration();
        conf.setSeries(new ListSeries(), new ListSeries());
        conf.addSeries(new ListSeries());
        assertEquals(
                "{\"chart\":{\"styledMode\":false},\"plotOptions\":{},\"series\":[{\"data\":[]},{\"data\":[]},{\"data\":[]}],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_setOptionsWithInactiveState_inactiveStatesSerialized() {
        Configuration conf = new Configuration();
        PlotOptionsPie options = new PlotOptionsPie();
        States states = options.getStates();
        Inactive inactive = states.getInactive();
        inactive.setOpacity(1.0);
        inactive.setBorderColor(new SolidColor("#000000"));
        inactive.setColor(new SolidColor("#808080"));
        inactive.setAnimation(false);
        conf.setPlotOptions(options);

        assertEquals(
                "{\"chart\":{\"styledMode\":false},\"plotOptions\":{\"pie\":{\"states\":{\"inactive\":{\"animation\":false,\"borderColor\":\"#000000\",\"color\":\"#808080\",\"opacity\":1.0}}}},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }
}
