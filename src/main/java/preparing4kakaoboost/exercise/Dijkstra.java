package preparing4kakaoboost.exercise;

import java.util.Arrays;

public class Dijkstra {
    private int[][] nodeToNodeInfo;
    private final int INF = 10000000;
    public void exec(){
        this.nodeToNodeInfo = new int[][]{
                {  0,   2,   5,   1, INF, INF}, // 내가 0번일때, 1번과의 거리, 2번과의 거리 3번 과의 거리가 나타나 있다. INF 는 알수 없는 영역이다.
                {  2,   0,   3,   2, INF, INF},
                {  5,   3,   0,   3,   1,   5},
                {  1,   2,   3,   0,   1, INF},
                {INF, INF,   1,   1,   0,   2},
                {INF, INF,   5, INF,   2,   0}
        };
        // answer = [0, 2, 3, 1, 2, 4]
        soulution (this.nodeToNodeInfo);
    }

    private int loopCnt = 0;
    private void soulution (int[][] arr){
        this.loopCnt = this.nodeToNodeInfo.length;
        this.arrVisiteYn = new boolean[]{false, false, false, false, false, false};
        this.distanceToNode = new int[]{INF, INF, INF, INF, INF, INF};

        // [# 1] 0번 인덱스에서 호출
        dijkstra(0); // 다익스트라 호출
        System.out.println(Arrays.toString(distanceToNode));
    }


    boolean[] arrVisiteYn; // 방문여부 - 영구보존용
    int[] distanceToNode; // 지정 Node 에서 각 노드별 거리정보 저장용 임시 배열
    private void dijkstra(int nodeNo){
        int minCourseIdx = 0;

        // [# 2] 방문 배열 정보 등록
        arrVisiteYn[nodeNo] = true; // 진입한 노드의 주변 거리정보를 계산완료했다는 플래그

        // [# 3] 검사대상 1차원 배열 복사
        for(int ix = 0; ix < loopCnt; ix++){
            distanceToNode[ix] = nodeToNodeInfo[nodeNo][ix]; // ARR 정보에서 distanceToNode (작업테이블)로 데이터 복사
        }

        // [# 4] 전체 노드를 순회 하기 위한 루프 진입
        for(int ix = 0; ix < loopCnt; ix++ ){ // 노드들의 갯수
            // [# 5] 임시 배열에서 가장 작은 경로값을 가진 index 정보를 리턴 받는다.
            minCourseIdx = getSmallestIndex();

            // [# 6] 가장 작은 경로 값을 가진 index 에 방문 정보를 확성 화 한다.
            arrVisiteYn[minCourseIdx] = true;

            // [#7] 아직 방문 정보가 없는 노드들을 위한 루프 진입
            for(int iy = 0; iy < loopCnt; iy++){
                // [#8] 아직 방문 하지 않은 노드들 중에서
                if(arrVisiteYn[iy] == false){
                    // [#9] 이 두 경로를 비교하여, (최소거리 + 다음노드 거리) < (직선거리) 와 같다면 해당 거리 값으로 갱신 처리
                    // [nodeNo -> minCourseIdx -> iy]  vs  [nodeNo -> iy]
                    if(distanceToNode[iy] > distanceToNode[minCourseIdx] + nodeToNodeInfo[minCourseIdx][iy]){
                        distanceToNode[iy] = distanceToNode[minCourseIdx] + nodeToNodeInfo[minCourseIdx][iy];
                    }

                    // nodeNo 에서 iy 로 연결 되어 있는 직선 경로가 있지만
                    // 최단 거리를 통해 2step 으로 갈 시 더 짧은 거리가 있을 수 있으므로
                    // 이 부분을 갱신하기 위한 절차
                }
            }

            // 결국 Loop 를 마치면 nodeNo(출발노드) -> 각 노드별로 갈 수 있는 최단 경로들이 다 완성 된다.
        }

    }

    // util : distanceToNode 배열에서 가장 적은 값을 찾아 리턴 한다.
    // 전체 loopCnt 를 순회하므로 시간복잡도 O(n) 호출 로직에 이 부과된다.
    private int getSmallestIndex(){
        int min = INF;
        int index = 0;
        for(int ix = 0; ix < loopCnt; ix++){ // 선형탐색 (Linear Search) <-> 이진탐색 (Binary Search)
            // 배열내에서 가장 작은 수를 찾아 min 을 갱신한다.
            if(distanceToNode[ix] < min){   // 최소거리값이 INF 가 아니면서
                if(arrVisiteYn[ix] == true) continue; // 하지만 이미 방문했던 경로인 경우 패스 한다. (호출 함수(dijkstra)에서 기본적으로 루트는 제외되어져 있음)
                min = distanceToNode[ix];
                index = ix;
            }
        }
        return index; // 가장 작은 수에 해당하는 인덱스 값을 리턴한다.
    }
}
