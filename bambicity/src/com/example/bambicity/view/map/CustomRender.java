package com.example.bambicity.view.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

class CustomRenderer<UserResponseModel extends ClusterItem> extends DefaultClusterRenderer<UserResponseModel>
{
    public CustomRenderer(Context context, GoogleMap map, ClusterManager<UserResponseModel> clusterManager) {
        super(context, map, clusterManager);            
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<UserResponseModel> cluster) {
        //start clustering if at least 2 items overlap
        return cluster.getSize() > 1;
    }
}
