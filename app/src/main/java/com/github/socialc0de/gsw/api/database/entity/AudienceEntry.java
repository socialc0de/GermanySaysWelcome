package com.github.socialc0de.gsw.api.database.entity;

import java.util.List;
import com.github.socialc0de.gsw.api.database.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.github.socialc0de.gsw.api.database.dao.AudienceEntryDao;
import com.github.socialc0de.gsw.api.database.dao.TranslationEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "AudienceEntry".
 */
public class AudienceEntry {

    private Long id;
    private Integer DID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient AudienceEntryDao myDao;

    private List<TranslationEntity> translations;

    public AudienceEntry() {
    }

    public AudienceEntry(Long id) {
        this.id = id;
    }

    public AudienceEntry(Long id, Integer DID) {
        this.id = id;
        this.DID = DID;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAudienceEntryDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDID() {
        return DID;
    }

    public void setDID(Integer DID) {
        this.DID = DID;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<TranslationEntity> getTranslations() {
        if (translations == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TranslationEntityDao targetDao = daoSession.getTranslationEntityDao();
            List<TranslationEntity> translationsNew = targetDao._queryAudienceEntry_Translations(id);
            synchronized (this) {
                if(translations == null) {
                    translations = translationsNew;
                }
            }
        }
        return translations;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetTranslations() {
        translations = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
