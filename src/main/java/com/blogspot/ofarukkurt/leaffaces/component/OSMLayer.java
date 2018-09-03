/*
 * Copyright 2018 Omer Faruk Kurt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.blogspot.ofarukkurt.leaffaces.component;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 15/03/2018 10:13:34
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".OSMLayer")
public class OSMLayer extends UIComponentBase {

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIComponent parent = this;

        while (!(parent instanceof Map)) {
            parent = parent.getParent();
        }

        Map mapComponent = (Map) parent;
        String mapVar = mapComponent.getWidgetVar();

        String layer = "var osmUrl = 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',"
                + "osm = L.tileLayer(osmUrl, {"
                + "maxZoom: 22,"
                + "attribution: '&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors'"
                + "});";
        String addLayer = mapVar + ".addLayer(osm);";

        writer.write(layer + addLayer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".OSMLayer";
    }

}
