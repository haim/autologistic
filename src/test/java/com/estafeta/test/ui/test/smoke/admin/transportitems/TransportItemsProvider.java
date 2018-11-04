package com.estafeta.test.ui.test.smoke.admin.transportitems;

import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.testng.annotations.DataProvider;

public class TransportItemsProvider {

  @DataProvider(name = "create_new_transport_item_by_type_car_transporter")
  public static Object[][] create_new_transport_item_by_type_car_transporter() {

    EnhancedRandom enhancedRandom =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();

    TransportItemsData carGeneratedData = enhancedRandom.nextObject(TransportItemsData.class);
    TransportItemsData trailerGeneratedData = enhancedRandom.nextObject(TransportItemsData.class);

    TransportItemsData car =
        new TransportItemsData.Builder()
            .active(true)
            .verified(true)
            .type("Car Transporter")
            .number(carGeneratedData.getNumber())
            .carrier("KATP 13061 PJSC")
            .trailer(false)
            .brand("DAF")
            .model("FT75CF")
            .description("вантажний сідловий тягач")
            .build();

    TransportItemsData trailer =
        new TransportItemsData.Builder()
            .active(true)
            .verified(true)
            .type("Car Transporter")
            .number(trailerGeneratedData.getNumber())
            .carrier("KATP 13061 PJSC")
            .trailer(true)
            .transportItemPartType("Eurolohr")
            .brand("LOHR")
            .model("1.21Е1")
            .description("Напівпричіп (автовоз)")
            .build();

    return new Object[][] {{car}};
  }

  @DataProvider(name = "create_new_transport_item_by_type_trailer")
  public static Object[][] create_new_transport_item_by_type_trailer() {

    EnhancedRandom enhancedRandom =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();

    TransportItemsData trailerGeneratedData = enhancedRandom.nextObject(TransportItemsData.class);

    TransportItemsData trailer =
        new TransportItemsData.Builder()
            .active(true)
            .verified(true)
            .type("Car Transporter")
            .number(trailerGeneratedData.getNumber())
            .carrier("KATP 13061 PJSC")
            .trailer(true)
            .transportItemPartType("Eurolohr")
            .brand("LOHR")
            .model("1.21Е1")
            .description("Напівпричіп (автовоз)")
            .build();

    return new Object[][] {{trailer}};
  }
}
