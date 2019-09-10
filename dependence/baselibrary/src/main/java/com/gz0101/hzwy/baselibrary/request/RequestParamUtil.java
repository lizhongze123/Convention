package com.gz0101.hzwy.baselibrary.request;


import com.gz0101.hzwy.baselibrary.R;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;


import java.util.Map;
import java.util.TreeMap;

public class RequestParamUtil {

    public static Map<String, String> createLocalParams(String... args) {

        if (args.length % 2 != 0) {
            ToastHelp.showShort(R.string.base_request_params_error);
        }

        TreeMap<String, String> paramsMap = new TreeMap<>();

        for (int i = 0; i < args.length; i++) {
            paramsMap.put(args[i], args[++i]);
        }

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            sb.append(entry.getKey())
                    .append('=')
                    .append(entry.getValue());
        }

        return paramsMap;
    }
}
