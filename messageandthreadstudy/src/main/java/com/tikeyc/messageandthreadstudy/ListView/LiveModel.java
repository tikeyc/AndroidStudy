package com.tikeyc.messageandthreadstudy.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by public1 on 2016/12/21.
 */

/*
  "dm_error": 0,
  "error_msg": "操作成功",
  "lives": [
    {
      "creator": {
        "id": 3191590,
        "level": 104,
        "gender": 0,
        "nick": "嘉。",
        "portrait": "MzI5NjYxNDYwMDQ4OTM0.jpg"
      },
      "id": "1482283715573873",
      "name": "",
      "city": "长春市",
      "share_addr": "http://mlive25.inke.cn/share/live.html?uid=3191590&liveid=1482283715573873&ctime=1482283715",
      "stream_addr": "http://pull99.a8.com/live/1482283715573873.flv",
      "version": 0,
      "slot": 5,
      "optimal": 0,
      "online_users": 17114,
      "group": 0,
      "link": 0,
      "multi": 0,
      "rotate": 0
    },
    {
      "creator": {
        "id": 4392328,
        "level": 63,
        "gender": 0,
        "nick": "伊洛。🍁",
        "portrait": "http://img2.inke.cn/MTQ4MjI0MTkyMzgzMyMzMTQjanBn.jpg"
      },
      "id": "1482283928878961",
      "name": "",
      "city": "",
      "share_addr": "http://mlive22.inke.cn/share/live.html?uid=4392328&liveid=1482283928878961&ctime=1482283928",
      "stream_addr": "http://pull99.a8.com/live/1482283928878961.flv",
      "version": 0,
      "slot": 5,
      "optimal": 0,
      "online_users": 13841,
      "group": 0,
      "link": 0,
      "multi": 0,
      "rotate": 0
    }
* */
public class LiveModel {

    public String dm_error;
    public String error_msg;

    public ArrayList<LiveInfoModel> lives;

}
