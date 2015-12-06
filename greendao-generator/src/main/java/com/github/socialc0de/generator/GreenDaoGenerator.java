package com.github.socialc0de.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class GreenDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.github.socialc0de.gsw.api.database.entity");
        schema.setDefaultJavaPackageDao("com.github.socialc0de.gsw.api.database.dao");
        schema.setDefaultJavaPackageTest("com.github.socialc0de.gsw.api.database.test");

        Entity translation = setupTranslationEntity(schema);
        Entity audienceEntity = setupAudienceEntity(schema, translation);


        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static Entity setupAudienceEntity(final Schema schema, Entity translation) {
        Entity entity = schema.addEntity("AudienceEntry");
        entity.setTableName("AudienceEntry");
        entity.addIdProperty();
        entity.addIntProperty("DID");

        Property translationID = translation.addLongProperty("translationID").notNull().getProperty();
        ToMany tt = entity.addToMany(translation, translationID);
        tt.setName("translations");

        return entity;
    }

    private static Entity setupTranslationEntity(final Schema schema) {
        Entity entity = schema.addEntity("TranslationEntity");
        entity.setTableName("TranslationEntity");
        entity.addIdProperty();
        entity.addStringProperty("language_code");
        entity.addStringProperty("name");
        entity.addStringProperty("description");
        entity.addStringProperty("question");
        entity.addStringProperty("answer");
        entity.addStringProperty("phrase");

        return entity;
    }




    /*
    private static Entity setupEntityCountry(Schema schema, Entity flags) {
        Entity country = schema.addEntity("Country");
        country.setTableName("country");
        country.addIdProperty();
        country.addStringProperty("country");
        country.addDoubleProperty("compsite_rate");
        country.addStringProperty("iso3");
        country.addStringProperty("hint");
        country.addIntProperty("zone");

        // fk to table flags
        Property fkFlagProperty = country.addLongProperty("fkFlag").columnName("fk_flag_id").getProperty();
        country.addToOne(flags, fkFlagProperty);

        return country;
    }
    */

}