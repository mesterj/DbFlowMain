package com.kite.joco.dbflowmainhelp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by Mester József on 2015.09.01..
 */
@Table(databaseName = ColonyDatabase.NAME)
public class QueenNoContainer extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    Long id;

    @Column
    String name;

    List<AntNoContainer> ants;

    @OneToMany(methods = {OneToMany.Method.SAVE, OneToMany.Method.DELETE}, variableName = "ants")
    public List<AntNoContainer> getMyAnts() {
            ants = new Select()
                    .from(AntNoContainer.class)
                    .where(Condition.column(AntNoContainer$Table.QUEEN_ID).is(id))
                    .queryList();
        return ants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
