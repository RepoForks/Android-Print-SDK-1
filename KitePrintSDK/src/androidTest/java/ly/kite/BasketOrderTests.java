/*****************************************************
 *
 * BasketOrderTests.java
 *
 *
 * Modified MIT License
 *
 * Copyright (c) 2017 Kite Tech Ltd. https://www.kite.ly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The software MAY ONLY be used with the Kite Tech Ltd platform and MAY NOT be modified
 * to be used with any competitor platforms. This means the software MAY NOT be modified 
 * to place orders with any competitors to Kite Tech Ltd, all orders MUST go through the
 * Kite Tech Ltd platform servers. 
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *****************************************************/

///// Package Declaration /////

package ly.kite;


///// Import(s) /////

import android.content.Context;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ly.kite.address.Address;
import ly.kite.address.Country;
import ly.kite.catalogue.Catalogue;
import ly.kite.catalogue.Product;
import ly.kite.journey.UserJourneyType;
import ly.kite.ordering.BasketItem;
import ly.kite.ordering.ImageSpec;
import ly.kite.ordering.Job;
import ly.kite.ordering.Order;
import ly.kite.ordering.OrderingDataAgent;


///// Class Declaration /////

/*****************************************************
 *
 * This class tests the full cycle from creation -> basket
 * -> order.
 *
 *****************************************************/
public class BasketOrderTests extends KiteTestCase
  {
  ////////// Static Constant(s) //////////

  @SuppressWarnings( "unused" )
  private static final String  LOG_TAG = "BasketOrderTests";


  ////////// Static Variable(s) //////////


  ////////// Member Variable(s) //////////


  ////////// Static Initialiser(s) //////////


  ////////// Static Method(s) //////////


  ////////// Constructor(s) //////////


  ////////// AndroidTestCase Method(s) //////////


  ////////// Method(s) //////////

  /*****************************************************
   *
   * CALENDAR tests.
   *
   *****************************************************/

  public void testCalendar1()
    {
    OrderingDataAgent orderingDataAgent = OrderingDataAgent.getInstance( getContext() );
    Product product = new Product( "product_id", "product_code", "Product Name", "Product Type", 0xff000000, UserJourneyType.CALENDAR, 2 );

    Catalogue catalogue = new Catalogue();
    catalogue.addProduct( "Group Label", null, product );

    HashMap<String,String> optionsMap = new HashMap<>();
    optionsMap.put( "Parameter1", "Alpha" );
    optionsMap.put( "Parameter2", "Bravo" );

    ImageSpec originalImageSpec1 = new ImageSpec( createSessionAssetFile(), null, 1 );
    ImageSpec originalImageSpec2 = new ImageSpec( createSessionAssetFile(), null, 2 );

    List<ImageSpec> originalImageSpecList = new ArrayList<>();
    originalImageSpecList.add( null );
    originalImageSpecList.add( originalImageSpec1 );
    originalImageSpecList.add( null );
    originalImageSpecList.add( null );
    originalImageSpecList.add( originalImageSpec2 );


    orderingDataAgent.clearDefaultBasket();

    orderingDataAgent.addItemSynchronously( OrderingDataAgent.CREATE_NEW_ITEM_ID, product, optionsMap, originalImageSpecList, 2 );

    List<BasketItem> basketItemList = orderingDataAgent.getAllItems( catalogue );

    Address shippingAddress = Address.getKiteTeamAddress();

    Order order = new Order( getContext(), basketItemList, shippingAddress, "info@kite.ly", "0123 456789", null );

    List<Job> jobList = order.getJobs();

    Assert.assertEquals( 2, jobList.size() );
    }


  /*****************************************************
   *
   * CIRCLE tests.
   *
   *****************************************************/

  public void testCircle1()
    {
    OrderingDataAgent orderingDataAgent = OrderingDataAgent.getInstance( getContext() );
    Product product = new Product( "product_id", "product_code", "Product Name", "Product Type", 0xff000000, UserJourneyType.CIRCLE, 2 );


    ImageSpec originalImageSpec1 = new ImageSpec( createSessionAssetFile(), null, 1 );
    ImageSpec originalImageSpec2 = new ImageSpec( createSessionAssetFile(), null, 2 );

    List<ImageSpec> originalImageSpecList = new ArrayList<>();
    originalImageSpecList.add( null );
    originalImageSpecList.add( originalImageSpec1 );
    originalImageSpecList.add( null );
    originalImageSpecList.add( null );
    originalImageSpecList.add( originalImageSpec2 );

    }


  ////////// Inner Class(es) //////////

  }