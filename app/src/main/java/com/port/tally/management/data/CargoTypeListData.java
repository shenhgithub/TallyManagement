package com.port.tally.management.data;
/**
 * Created by 超悟空 on 2015/9/21.
 */

import android.util.Log;

import com.port.tally.management.bean.CargoType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 货物列表任务数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/9/21
 * @since 1.0
 */
public class CargoTypeListData extends SimpleJsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "CargoTypeListData.";

    /**
     * 货物列表
     */
    private List<CargoType> cargoTypeList = null;

    /**
     * 获取货物列表
     *
     * @return 货物列表
     */
    public List<CargoType> getCargoTypeList() {
        return cargoTypeList;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {

    }

    @Override
    protected void onExtractData(JSONObject jsonData) throws Exception {
        JSONArray jsonArray = jsonData.getJSONArray("Data");
        Log.i(LOG_TAG + "onExtractData", "get cargoTypeList count is " + jsonArray.length());

        // 新建货物列表
        cargoTypeList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONArray jsonRow = jsonArray.getJSONArray(i);

            if (jsonRow.length() > 2) {
                // 一条货物数据
                CargoType cargoType = new CargoType();
                cargoType.setId(jsonRow.getString(0));
                cargoType.setName(jsonRow.getString(1));
                cargoType.setShortCode(jsonRow.getString(2));

                // 加入列表
                cargoTypeList.add(cargoType);
            }
        }

        Log.i(LOG_TAG + "onExtractData", "cargo type list count is " + cargoTypeList.size());
    }
}
