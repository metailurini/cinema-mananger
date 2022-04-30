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

    public Location(ResultSet result) throws SQLException {
        this.LocationId = result.getString("location_id");
        this.Name = result.getString("name");
        this.CreatedAt = result.getTimestamp("created_at");
        this.UpdatedAt = result.getTimestamp("updated_at");
        this.DeletedAt = result.getTimestamp("deleted_at");
        this.ResourcePath = result.getString("resource_path");
        this.LocationType = result.getString("location_type");
        this.PartnerInternalId = result.getString("partner_internal_id");
        this.PartnerInternalParentId = result.getString("partner_internal_parent_id");
        this.ParentLocationId = result.getString("parent_location_id");
        this.IsArchived = result.getBoolean("is_archived");
        this.AccessPath = result.getString("access_path");
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
