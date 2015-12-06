package com.github.socialc0de.gsw.api.database;

import android.content.Context;
import android.database.DatabaseUtils;
import android.util.Log;

import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.api.database.dao.DaoSession;
import com.github.socialc0de.gsw.api.database.dao.PhraseCategoryDao;
import com.github.socialc0de.gsw.api.database.dao.TranslationEntityDao;
import com.github.socialc0de.gsw.customClasses.api.Language;
import com.github.socialc0de.gsw.customClasses.api.PhraseCategory;
import com.github.socialc0de.gsw.customClasses.api.Translations;

import java.util.ArrayList;



public class GswDB {

    public static void savePhraseCategory(final ArrayList<PhraseCategory> phraseCategories) throws Exception {

        if (phraseCategories != null && phraseCategories.isEmpty()) {
            return;
        }

        final DaoSession daoSession = GreenDaoHelper.getDaoSession(MainActivity.getMainActivity(), GreenDaoHelper.DB_NAME);
        final PhraseCategoryDao phraseCategoryDao = daoSession.getPhraseCategoryDao();
        final TranslationEntityDao translationEntityDao = daoSession.getTranslationEntityDao();

        daoSession.runInTx(new Runnable() {
            public void run() {
                for (PhraseCategory phraseCategory : phraseCategories) {

                    translationEntityDao.insertInTx(getTranslationEntity(phraseCategory.getTranslations().getAr()));
                    translationEntityDao.insertInTx(getTranslationEntity(phraseCategory.getTranslations().getDe()));
                    translationEntityDao.insertInTx(getTranslationEntity(phraseCategory.getTranslations().getEn()));
                    translationEntityDao.insertInTx(getTranslationEntity(phraseCategory.getTranslations().getFr()));

                    phraseCategoryDao.insertInTx(getPhraseCategoryDatabase(phraseCategory));

                }
                daoSession.clear();
                Log.d("TEST", "insert finished");
            }
        });
    }

    private static com.github.socialc0de.gsw.api.database.entity.PhraseCategory getPhraseCategoryDatabase(PhraseCategory phraseCategory){
        com.github.socialc0de.gsw.api.database.entity.PhraseCategory phraseCategoryDB = new com.github.socialc0de.gsw.api.database.entity.PhraseCategory();
        phraseCategoryDB.setDID(phraseCategory.getId());
        return phraseCategoryDB;
    }

    private static com.github.socialc0de.gsw.api.database.entity.TranslationEntity getTranslationEntity(Language language){
        com.github.socialc0de.gsw.api.database.entity.TranslationEntity translationEntity = new com.github.socialc0de.gsw.api.database.entity.TranslationEntity();

        translationEntity.setLanguage_code(language.getClass().getName().toLowerCase());

        translationEntity.setAnswer(language.getAnswer());
        translationEntity.setDescription(language.getDescription());
        translationEntity.setName(language.getName());
        translationEntity.setPhrase(language.getPhrase());
        translationEntity.setQuestion(language.getQuestion());
        
        return translationEntity;
    }



    /*
    public static void  saveCurrencies(Context context, final Kurse currencies) throws Exception {
        if (currencies == null) {
            return;
        }

        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CurrencyDao currencyDao = daoSession.getCurrencyDao();

        daoSession.runInTx(new Runnable() {
            public void run() {
                for (int i=0; i< currencies.length; i++) {
                    Currency currency = currencies[i];
                    currencyDao.insertInTx(currency);
                }
                daoSession.clear();
                Log.d("TEST", "insert finished");
            }
        });
    }
*/
    /*

    public static List<Product> loadProductsFromDatabase(Context context, String filterString) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final ProductDao productDao = daoSession.getProductDao();

        if (filterString == null || filterString.equals("")) {
            return productDao.queryBuilder().orderAsc(ProductDao.Properties.Product_name).list();
        } else {

            String escapedSearchText = DatabaseUtils.sqlEscapeString(filterString);
            // delete first "'" and last "'"
            escapedSearchText = escapedSearchText.substring(1, escapedSearchText.length() - 1);

            // there is a bug in sqlite: Umlaute won't be changed by sql function "upper()" or "lower()". See http://sqlite.org/faq.html#q18
            List<Product> resultList = productDao.queryRaw("where " + "(" + "lower(product_name) like '%" + escapedSearchText.toLowerCase() + "%'" + " OR " + "upper(product_name) like '%" + escapedSearchText.toUpperCase() + "%'" + ")", null);

            return resultList;
        }
    }

    public static List<Product> loadProductsFromDatabase(Context context, Long categoryId) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final ProductDao productDao = daoSession.getProductDao();

        if (categoryId == null) {
            return productDao.queryBuilder().list();
        } else {
            return productDao.queryBuilder().where(ProductDao.Properties.CategoryId.eq(categoryId)).orderAsc(ProductDao.Properties.Product_name).list();
        }
    }

    public static Product loadProductFromDatabase(Context context, Long id) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final ProductDao productDao = daoSession.getProductDao();

        if (id == null) {
            return null;
        } else {
            Product product = productDao.queryBuilder().where(ProductDao.Properties.Id.eq(id)).unique();
            return product;
        }
    }

    public static List<Category> loadCategoriesFromDatabase(Context context) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CategoryDao categoryDao = daoSession.getCategoryDao();

        List<Category> categories = categoryDao.loadAll();

        return categories;
    }

    public static Category loadCategoryFromDatabase(Context context, Long id) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CategoryDao categoryDao = daoSession.getCategoryDao();

        if (id == null) {
            return null;
        } else {
            Category category = categoryDao.queryBuilder().where(CategoryDao.Properties.Id.eq(id)).unique();
            return category;
        }
    }

    public static List<Country> loadCountriesFromDatabase(Context context) throws Exception {
        return loadCountriesFromDatabase(context, null);
    }

    public static List<Country> loadCountriesFromDatabase(Context context, String filterString) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CountryDao countryDao = daoSession.getCountryDao();

        if (filterString == null || filterString.equals("")) {
            return countryDao.queryBuilder().orderAsc(CountryDao.Properties.Country).list();
        } else {
            String escapedSearchText = DatabaseUtils.sqlEscapeString(filterString);
            // delete first "'" and last "'"
            escapedSearchText = escapedSearchText.substring(1, escapedSearchText.length() - 1);

            // there is a bug in sqlite: Umlaute won't be changed by sql function "upper()" or "lower()". See http://sqlite.org/faq.html#q18
            List<Country> resultList = countryDao.queryRaw("where " + "(" + "lower(country) like '%" + escapedSearchText.toLowerCase() + "%'" + " OR " + "upper(country) like '%" + escapedSearchText.toUpperCase() + "%'" + ")" +
                    " OR " + "(" + "lower(hint) like '%" + escapedSearchText.toLowerCase() + "%'" + " OR " + "upper(hint) like '%" + escapedSearchText.toUpperCase() + "%'" + ")", null);
            return resultList;
        }
    }

    public static Country loadCountryFromDatabase(Context context, Long id) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CountryDao countryDao = daoSession.getCountryDao();

        if (id == null) {
            return null;
        } else {
            Country country = countryDao.queryBuilder().where(CountryDao.Properties.Id.eq(id)).unique();
            return country;
        }
    }

    public static List<Currency> loadCurrenciesFromDatabase(Context context, String filterString) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CurrencyDao currencyDao = daoSession.getCurrencyDao();

        if (filterString == null || filterString.equals("")) {
            return currencyDao.queryBuilder().orderAsc(CurrencyDao.Properties.Name).list();
        } else {
            String escapedSearchText = DatabaseUtils.sqlEscapeString(filterString);
            // delete first "'" and last "'"
            escapedSearchText = escapedSearchText.substring(1, escapedSearchText.length() - 1);

            // there is a bug in sqlite: Umlaute won't be changed by sql function "upper()" or "lower()". See http://sqlite.org/faq.html#q18
            List<Currency> resultList = currencyDao.queryRaw("where " + "(" + "lower(name) like '%" + escapedSearchText.toLowerCase() + "%'" + " OR " + "upper(name) like '%" + escapedSearchText.toUpperCase() + "%'" + " OR " + "upper(iso3) like '%" + escapedSearchText.toUpperCase() + "%'" + ")", null);
            return resultList;
        }
    }

    public static Currency loadCurrencyFromDatabase(Context context, long currencyId) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CurrencyDao currencyDao = daoSession.getCurrencyDao();

        return  currencyDao.queryBuilder().where(CurrencyDao.Properties.Id.eq(currencyId)).unique();
    }

    public static Currency loadCurrencyFromDatabase(Context context, String iso3) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CurrencyDao currencyDao = daoSession.getCurrencyDao();

        return  currencyDao.queryBuilder().where(CurrencyDao.Properties.Iso3.eq(iso3)).unique();
    }
    */

    /**
     * Euro is not in content data, so we must add it manually
     *
     * @param context
     */
    /*
    public static void addEuroToCurrencyDatabase(Context context) throws Exception{
        // add euro to database
        Currency euro = new Currency();
        euro.setKurswert(1d);
        euro.setName("Euro");
        euro.setIso3("EUR");
        GswDB.addCurrency(context, euro);
    }

    private static long addCurrency(Context context, Currency currency) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final CurrencyDao currencyDao = daoSession.getCurrencyDao();

        if (currency == null) {
            return 0;
        }
        return currencyDao.insert(currency);
    }

    public static List<Testcriterion> loadTestcritetionsFromDatabase(Context context) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final TestcriterionDao testcriterionDao = daoSession.getTestcriterionDao();

        return testcriterionDao.queryBuilder().orderAsc(TestcriterionDao.Properties.Criterion_title).list();
    }

    public static Testcriterion loadTestcritetionFromDatabase(Context context, long id) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final TestcriterionDao testcriterionDao = daoSession.getTestcriterionDao();

        return testcriterionDao.queryBuilder().where(TestcriterionDao.Properties.Id.eq(id)).unique();
    }

    public static List<Question> loadQuestionsFromDatabase(Context context) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final QuestionDao questionDao = daoSession.getQuestionDao();

        return questionDao.queryBuilder().orderAsc(QuestionDao.Properties.Order_id).list();
    }

    public static Contact loadOfficeFromDatabase(Context context, Integer id) throws Exception {
        final DaoSession daoSession = GreenDaoHelper.getDaoSession(context, GreenDaoHelper.DB_NAME);
        final ContactDao contactDao = daoSession.getContactDao();

        if (id == null) {
            return null;
        } else {
            Contact contact = contactDao.queryBuilder().where(ContactDao.Properties.Office_number.eq(id)).unique();
            return contact;
        }
    }
    */

}
