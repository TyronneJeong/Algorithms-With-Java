package programmers.assignment.kakao2021.domain;

import programmers.assignment.kakao2021.controller.MainController;

public class ServiceMap {
    private static ServiceMap serviceMap;
    private static int[][] mapArr;

    /* Constructor */
    public ServiceMap() {}

    /**
     * singletone instance
     * @return
     */
    public static ServiceMap getInstance(){
        if(serviceMap != null){
            return serviceMap;
        } else {
            return new ServiceMap();
        }
    }

    /**
     * initMap
     * @param type
     */
    public static void initMap(String type){
        if(type.equals("1")) {
            mapArr = new int[5][5];
        } else {
            mapArr = new int[60][60];
        }
        for (int i = 0; i < mapArr.length; i++) {
            for (int j = 0; j < mapArr.length; j++) {
                mapArr[i][j] = 0;
            }
        }
    }

    /**
     * getMap
     */
    public static int[][] getMap() {
        return mapArr;
    }

    /**
     * setMap
     * @param locX, locY, value
     * @param value
     */
    public static void setMap(int locX, int locY, int value){
        mapArr[locX][locY] = value;
    }




    @Override
    public String toString() {
        System.out.println("## Print Map ##");
        int loopCnt = mapArr.length;
        for (int i = 0; i < loopCnt; i++) {
            for (int j = 0; j < loopCnt; j++) {
                System.out.print(String.format("%3d", mapArr[i][j]));
            }
            System.out.println();
        }
        return super.toString();
    }
}
