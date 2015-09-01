package com.kite.joco.dbflowmainhelp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Mester József on 2015.09.01..
 */
@Table(databaseName = ColonyDatabase.NAME)
public class AntNoContainer extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    Long id;

    @Column
    String type;

    @Column
    boolean male;

    // First think...
    @Column
    long queen_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Long getQueen_id() {
        return queen_id;
    }

    public void setQueen_id(Long queen_id) {
        this.queen_id = queen_id;
    }
}
