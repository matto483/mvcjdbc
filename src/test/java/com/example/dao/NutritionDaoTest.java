package com.example.dao;

import com.example.domain.Nutrition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionDaoTest {

    Random random = new Random();

    @Autowired
    NutritionDao nutritionDao;

    @Test
    public void testCreate() {
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionDao.add(nutrition);

        List<Nutrition> nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        boolean found = false;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + nutrition, found);
    }

    @Test
    public void testFind() {
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        //creates a row in the db
        nutritionDao.add(nutrition);

        //return back a List of the db entries...
        List<Nutrition> nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNutrition = null;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                foundNutrition = nut;
                break;
            }
        }

        Nutrition comparisonNut = nutritionDao.find(foundNutrition.getId());
        Assert.assertTrue("Errr, they should be the same", foundNutrition.equals(comparisonNut) );
    }

    @Test
    public void testDelete(){
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionDao.add(nutrition);

        List<Nutrition> nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNut = null;
        for (Nutrition nut : nutritions) {
            if (nut.equals(nutrition)) {
                foundNut = nut;
                break;
            }
        }

        Assert.assertNotNull(foundNut);
        nutritionDao.delete(foundNut.getId());
        Assert.assertNull("Should not find anything.. ", nutritionDao.find(foundNut.getId()));
    }

    @Test
    public void testUpdate(){
        //make a new one.
        Nutrition nut = new Nutrition();
        nut.setCalories(random.nextInt());
        nut.setCarbs(random.nextInt());
        nut.setProduct("Orange");
        //put it in the list
        nutritionDao.add(nut);

        //find it in the list, (like in delete)
        List<Nutrition> nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        Nutrition foundNut = null;
        for (Nutrition n : nutritions) {
            if (n.equals(nut)) {
                foundNut = n;
                break;
            }
        }
        Assert.assertNotNull(foundNut);
        //change some of its fields...
        foundNut.setCarbs(random.nextInt());
        foundNut.setCalories(random.nextInt());
        //call update.
        nutritionDao.update(foundNut);

        //call find again and compare
        Nutrition foundNut2 = null;
        nutritions = nutritionDao.findAll();
        Assert.assertNotNull(nutritions);
        Assert.assertTrue(nutritions.size() > 0);
        for (Nutrition n : nutritions) {
            if (n.equals(foundNut)) {
                foundNut2 = n;
                break;
            }
        }
        Assert.assertEquals(foundNut,foundNut2);

    }
    @Test
    public void multiNewTest(){
        Nutrition nutrition = new Nutrition();

        String product = Integer.toString(random.nextInt());
        nutrition.setProduct(product);

        int calories = random.nextInt();
        nutrition.setCalories(calories);

        int carbs = random.nextInt();
        nutrition.setCarbs(carbs);

        nutritionDao.add(nutrition);

        Nutrition nutrition2 = new Nutrition();

         product = Integer.toString(random.nextInt());
        nutrition2.setProduct(product);

         calories = random.nextInt();
        nutrition2.setCalories(calories);

         carbs = random.nextInt();
        nutrition2.setCarbs(carbs);

        nutritionDao.add(nutrition2);

        Assert.assertFalse("Shoulda have been different...", nutrition.equals(nutrition2));


    }
}
