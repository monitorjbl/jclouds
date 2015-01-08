/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.nova.v2_0.config;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jclouds.openstack.nova.v2_0.domain.Image;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

@Test(groups = "unit")
public class ImageAdapterTest {

   private Gson gson;

   @BeforeTest
   public void setup(){
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(Image.class, new NovaParserModule.ImageAdapter());
      gson = gsonBuilder.create();
   }

   public void testDeserializeWithBlockDeviceMappingAndMetadata() throws Exception {
      Image img = gson.fromJson(stringFromResource("image_list_with_block_device_mapping.json"), Image.class);
      assertNotNull(img.getMetadata());
      assertEquals(10, img.getMetadata().size());
      assertNotNull(img.getBlockDeviceMapping());
      assertEquals(1, img.getBlockDeviceMapping().size());
   }

   public void testDeserializeWithoutBlockDeviceMappingOrMetadata(){
      //TODO
   }

   public void testDeserializeWithoutBlockDeviceMapping(){
      //TODO
   }

   public void testDeserializeWithoutMetadata(){
      //TODO
   }

   private String stringFromResource(String resource) throws IOException {
      return Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
   }

   public static class ImageContainer{
      public Image image;
      public List<Image> images;
   }
}
