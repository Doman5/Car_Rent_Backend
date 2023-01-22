package pl.domanski.carRent.admin.car.service.utils;

import org.junit.jupiter.api.Test;
import pl.domanski.carRent.admin.car.model.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;
import pl.domanski.carRent.admin.car.utils.CarUpdateUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewDescriptionsList;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewDescriptionsListWithOneMoreValue;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewEquipmentList;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewEquipmentListWithOneMoreValue;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewShorterDescriptionsList;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createNewShorterEquipmentList;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createOldDescriptionsList;
import static pl.domanski.carRent.admin.car.service.utils.generateValues.carUpdateUtilsGenerateValues.createOldEquipmentList;

class CarUpdateUtilsTest {

    @Test
    void should_replace_old_Descriptions() {
        //given
        List<AdminCarDescription> oldCarDescriptions = createOldDescriptionsList();
        List<AdminCarDescriptionDto> newCarDescriptions = createNewDescriptionsList();
        //when
        ArrayList<AdminCarDescription> result = CarUpdateUtils.setNewCarDescriptionValues(newCarDescriptions, oldCarDescriptions);
        //then
        assertEquals(newCarDescriptions.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(newCarDescriptions.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(newCarDescriptions.get(2).getDescription(), result.get(2).getDescription());
    }

    @Test
    void should_return_shorter_descriptions_list() {
        //given
        List<AdminCarDescription> oldCarDescriptions = createOldDescriptionsList();
        List<AdminCarDescriptionDto> newCarDescriptions = createNewShorterDescriptionsList();
        //when
        ArrayList<AdminCarDescription> result = CarUpdateUtils.setNewCarDescriptionValues(newCarDescriptions, oldCarDescriptions);
        //then
        assertEquals(newCarDescriptions.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(newCarDescriptions.get(1).getDescription(), result.get(1).getDescription());
        assertThat(result, hasSize(2));
    }


    @Test
    void should_add_new_description_to_list() {
        //given
        List<AdminCarDescription> oldCarDescriptions = createOldDescriptionsList();
        List<AdminCarDescriptionDto> newCarDescriptions = createNewDescriptionsListWithOneMoreValue();
        //when
        ArrayList<AdminCarDescription> result = CarUpdateUtils.setNewCarDescriptionValues(newCarDescriptions, oldCarDescriptions);
        //then
        assertEquals(newCarDescriptions.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(newCarDescriptions.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(newCarDescriptions.get(2).getDescription(), result.get(2).getDescription());
        assertEquals(newCarDescriptions.get(3).getDescription(), result.get(3).getDescription());
    }


    @Test
    void should_replace_old_Equipment() {
        //given
        List<AdminCarEquipment> oldCarEquipment = createOldEquipmentList();
        List<AdminCarEquipmentDto> newCarEquipment = createNewEquipmentList();
        //when
        ArrayList<AdminCarEquipment> result = CarUpdateUtils.setNewCarEquipmentValues(newCarEquipment, oldCarEquipment);
        //then
        assertEquals(newCarEquipment.get(0).getName(), result.get(0).getName());
        assertEquals(newCarEquipment.get(1).getName(), result.get(1).getName());
        assertEquals(newCarEquipment.get(2).getName(), result.get(2).getName());
    }



    @Test
    void should_return_shorter_equipments_list() {
        //given
        List<AdminCarEquipment> oldCarEquipment = createOldEquipmentList();
        List<AdminCarEquipmentDto> newCarEquipment = createNewShorterEquipmentList();
        //when
        ArrayList<AdminCarEquipment> result = CarUpdateUtils.setNewCarEquipmentValues(newCarEquipment, oldCarEquipment);
        //then
        assertEquals(newCarEquipment.get(0).getName(), result.get(0).getName());
        assertEquals(newCarEquipment.get(1).getName(), result.get(1).getName());
        assertThat(result, hasSize(2));
    }


    @Test
    void should_add_new_equipment_to_list() {
        //given
        List<AdminCarEquipment> oldCarEquipment = createOldEquipmentList();
        List<AdminCarEquipmentDto> newCarEquipment = createNewEquipmentListWithOneMoreValue();
        //when
        ArrayList<AdminCarEquipment> result = CarUpdateUtils.setNewCarEquipmentValues(newCarEquipment, oldCarEquipment);
        //then
        assertEquals(newCarEquipment.get(0).getName(), result.get(0).getName());
        assertEquals(newCarEquipment.get(1).getName(), result.get(1).getName());
        assertEquals(newCarEquipment.get(2).getName(), result.get(2).getName());
        assertEquals(newCarEquipment.get(3).getName(), result.get(3).getName());
    }


}