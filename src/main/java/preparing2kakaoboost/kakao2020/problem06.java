package preparing2kakaoboost.kakao2020;

//문제 6
//정답률: 0.6%
//문제6 풀러 가기
//레스토랑을 운영하고 있는 “스카피”는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다. 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.
//
//레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다. 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다. 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다. 친구들이 1시간 동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다. 편의 상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다. 또, 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.
//
//외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist가 매개변수로 주어질 때, 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.
//
//
//제한사항
//n은 1 이상 200 이하인 자연수입니다.
//weak의 길이는 1 이상 15 이하입니다.
//서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
//취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
//weak의 원소는 0 이상 n – 1 이하인 정수입니다.
//dist의 길이는 1 이상 8 이하입니다.
//dist의 원소는 1 이상 100 이하인 자연수입니다.
//친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.
//
//입출력 예
//n	weak	dist	result
//12	[1, 5, 6, 10]	[1, 2, 3, 4]	2
//12	[1, 3, 4, 9, 10]	[3, 5, 7]	1
//
//입출력 예에 대한 설명
//입출력 예 #1 원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.친구들을 투입하는 예시 중 하나는 다음과 같습니다.
//4m를 이동할 수 있는 친구는 10m 지점에서 출발해 시계방향으로 돌아 1m 위치에 있는 취약 지점에서 외벽 점검을 마칩니다.
//2m를 이동할 수 있는 친구는 4.5m 지점에서 출발해 6.5m 지점에서 외벽 점검을 마칩니다.
//그 외에 여러 방법들이 있지만, 두 명보다 적은 친구를 투입하는 방법은 없습니다. 따라서 친구를 최소 두 명 투입해야 합니다.
//입출력 예 #2 원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.7m를 이동할 수 있는 친구가 4m 지점에서 출발해 반시계 방향으로 점검을 돌면 모든 취약 지점을 점검할 수 있습니다. 따라서 친구를 최소 한 명 투입하면 됩니다.
//
//출제 의도
//원형으로 주어진 완전탐색 문제를 해결할 수 있는지 파악
//bit mask나, permutation 등을 활용할 수 있는지 파악
//
//해설
//dist의 길이가 최대 8로 크지 않기 때문에, 가능한 모든 방법을 탐색해서 해결할 수 있습니다. 따라서 dist에서 친구 한 명을 선택해 나열하는 방법, 친구 두 명을 선택해 나열하는 방법 … 친구 여덟 명을 선택해 나열하는 방법을 모두 고려해주면 됩니다.
//
//이제 각각의 방법에 대해 취약지점을 모두 점검할 수 있는지 확인합니다. 먼저 점검해야 될 벽이 원형이 아니라 직선이라고 가정해 보면, 모든 취약지점을 점검하기 위해서는 시작 지점부터 순서대로 배정해야 된다는 점을 알 수 있습니다. 친구 한 명을 배정한 다음에는 아직 점검하지 않은 지점 중에서 바로 다음 지점에 친구를 순서대로 배정하면 됩니다.
//
//배정을 마친 후에도 아직 점검하지 않은 취약지점이 남아있다면 해당 배치 방법으로는 모든 취약지점을 점검할 수 없다는 뜻입니다. 이제, 원형으로 이루어진 벽을 고려하기 위해, 다음 시작 지점을 기준으로 직선으로 펼쳐주면 됩니다.
//
//예를 들어, N = 12, weak = [1, 5, 6, 10]인 경우 처음에 위치 1을 기준으로 직선으로 펼쳤다면, 이번에는 위치 5를 기준으로 [5, 6, 10, 13]과 같이 직선 형태로 만들어 주면 됩니다. 이때, 13은 값이 증가하는 형태로 만들어 주기 위해 1 + 12를 해준 결과입니다.
//
//이제 각 친구들을 선택해 나열하는 방법에 대해서 모든 시작 지점에 대해 직선으로 펼친 후 취약 지점에 배정해본 다음, 그중 가장 적은 친구들을 선택하는 방법을 찾으면 됩니다.

public class problem06 {
    public void exec() {
        solution();
    }

    private void solution() {
        System.out.println("hi");
    }
}