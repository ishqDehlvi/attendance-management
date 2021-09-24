package com.sujin.clickableimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by Techolabz on 13-11-2016.
 */
public class BLEAdapter extends ArrayAdapter<BLE> {
    Context context;
    ArrayList<BLE> bleArrayList;

    public BLEAdapter(Context context, ArrayList<BLE> bleArrayList) {
        super(context, R.layout.row_structure, bleArrayList);
        this.context=context;
        this.bleArrayList = bleArrayList;
    }

    public static class DataHolder{
        public TextView txtAdddress;
        public TextView txtName;
        public TextView txtuuid;
        public TextView txtmajor;
        public TextView txtminor;
        public TextView txtrssi;
        public TextView txtnamespaceid;
        public TextView txtinstanceid;
        public TextView txturl;
        public TextView txtrawdata;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataHolder holder=null;
        if(convertView == null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row_structure,null);

            holder=new DataHolder();

            holder.txtAdddress=(TextView)convertView.findViewById(R.id.txtAddress);
            holder.txtName=(TextView)convertView.findViewById(R.id.txtName);
            holder.txtuuid=(TextView)convertView.findViewById(R.id.txtuuid);
            holder.txtmajor=(TextView)convertView.findViewById(R.id.txtmajor);
            holder.txtminor=(TextView)convertView.findViewById(R.id.txtminor);
            holder.txtrssi=(TextView)convertView.findViewById(R.id.txtrssi);
            holder.txtnamespaceid=(TextView)convertView.findViewById(R.id.txtnamespaceid);
            holder.txtinstanceid=(TextView)convertView.findViewById(R.id.txtinstanceid);
            holder.txturl=(TextView)convertView.findViewById(R.id.txturl);
            holder.txtrawdata=(TextView)convertView.findViewById(R.id.txtrawdata);

            convertView.setTag(holder);
        }

        else{

            holder=(DataHolder)convertView.getTag();
        }

       BLE ble= bleArrayList.get(position);


        holder.txtAdddress.setText("Address : "+ble.getDeviceAddress());
        holder.txtuuid.setText("Uuid : "+ble.getUuid());
        holder.txtmajor.setText("Major : "+ble.getMajor());
        holder.txtminor.setText("Minor : "+ble.getMinor());
        holder.txtrssi.setText("Rssi : "+ble.getRssi());
        holder.txtnamespaceid.setText("NamespaceId : "+ble.getNamespaceid());
        holder.txtinstanceid.setText("InstanceId : "+ble.getInstanceid());
        holder.txturl.setText("Url : "+ble.getUrl());
        holder.txtrawdata.setText("Raw Data : "+ble.getRawData());


        if(ble.getDeviceName()==null){
            holder.txtName.setText("Name : "+"N/A");
        }
        else{
            holder.txtName.setText("Name : "+ble.getDeviceName());
        }

        if(ble.getNamespaceid()==null){
            holder.txtnamespaceid.setVisibility(View.GONE);
            holder.txtinstanceid.setVisibility(View.GONE);

        }

        else {
            holder.txtnamespaceid.setVisibility(View.VISIBLE);
            holder.txtinstanceid.setVisibility(View.VISIBLE);
            holder.txtName.setText("Name : "+ble.getDeviceName()+" (Eddystone UID)");

        }


        if(ble.getUuid()==null || ble.getUuid()==""){
            holder.txtuuid.setVisibility(View.GONE);
            holder.txtmajor.setVisibility(View.GONE);
            holder.txtminor.setVisibility(View.GONE);


        }

        else {
            holder.txtuuid.setVisibility(View.VISIBLE);
            holder.txtmajor.setVisibility(View.VISIBLE);
            holder.txtminor.setVisibility(View.VISIBLE);

            holder.txtName.setText("Name : "+ble.getDeviceName()+" (iBeacon)");

        }
        if(ble.getUrl()==null){
            holder.txturl.setVisibility(View.GONE);


        }
        else{
            holder.txturl.setVisibility(View.VISIBLE);
            holder.txtName.setText("Name : "+ble.getDeviceName()+" (Eddystone Url)");


        }


        if(ble.getRawData()==null){
            holder.txtrawdata.setVisibility(View.GONE);


        }
        else{
            holder.txtrawdata.setVisibility(View.VISIBLE);
            holder.txtName.setText("Name : "+ble.getDeviceName()+" (Raw Data)");


        }


        return convertView;
    }
}
