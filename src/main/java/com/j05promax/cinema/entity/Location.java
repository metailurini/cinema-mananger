package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Location extends Entity {

    public String LocationId = null;
    public String Name = null;
    public Timestamp CreatedAt = null;
    public Timestamp UpdatedAt = null;
    public Timestamp DeletedAt = null;
    public String ResourcePath = null;
    public String LocationType = null;
    public String PartnerInternalId = null;
    public String PartnerInternalParentId = null;
    public String ParentLocationId = null;
    public Boolean IsArchived = null;
    public String AccessPath = null;


    public static String TableName() {
        return "locations";
    };

    public Location FromResultSet(ResultSet result) throws SQLException {
        LocationId = result.getString("location_id");
        Name = result.getString("name");
        CreatedAt = result.getTimestamp("created_at");
        UpdatedAt = result.getTimestamp("updated_at");
        DeletedAt = result.getTimestamp("deleted_at");
        ResourcePath = result.getString("resource_path");
        LocationType = result.getString("location_type");
        PartnerInternalId = result.getString("partner_internal_id");
        PartnerInternalParentId = result.getString("partner_internal_parent_id");
        ParentLocationId = result.getString("parent_location_id");
        IsArchived = result.getBoolean("is_archived");
        AccessPath = result.getString("access_path");
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "LocationId='" + LocationId + '\'' +
                ", Name='" + Name + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", UpdatedAt=" + UpdatedAt +
                ", DeletedAt=" + DeletedAt +
                ", ResourcePath='" + ResourcePath + '\'' +
                ", LocationType='" + LocationType + '\'' +
                ", PartnerInternalId='" + PartnerInternalId + '\'' +
                ", PartnerInternalParentId='" + PartnerInternalParentId + '\'' +
                ", ParentLocationId='" + ParentLocationId + '\'' +
                ", IsArchived=" + IsArchived +
                ", AccessPath='" + AccessPath + '\'' +
                '}';
    }
}
