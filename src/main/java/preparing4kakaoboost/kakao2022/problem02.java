package preparing4kakaoboost.kakao2022;

//문제 2 – k 진수에서 소수의 개수 구하기
//정답률 : 55.82%
//문제 2 풀러가기
//
//
//문제 설명
//
//양의 정수 n이 주어집니다. 이 숫자를 k 진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.
//
//0P0처럼 소수 양쪽에 0이 있는 경우
//P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
//0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
//P처럼 소수 양쪽에 아무것도 없는 경우
//단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
//예를 들어, 101은 P가 될 수 없습니다.
//
//
//예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.
//
//정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.
//
//
//제한사항
//
//1 ≤ n ≤ 1,000,000
//3 ≤ k ≤ 10
//
//입출력 예
//
//n	k	result
//437674	3	3
//110011	10	2
//입출력 예 설명
//
//• 입출력 예 #1
//
//문제 예시와 같습니다.
//
//• 입출력 예 #2
//
//110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.
//
//
//문제 풀이
//
//이 문제는 진법 변환 후에 변환된 숫자를 0을 기준으로 파싱하고, 파싱 한 숫자를 소수 판별해 해결할 수 있는 문제입니다.
//
//소수 판별하는 데에는 대표적으로 2가지 방법이 있습니다. 첫 번째로 에라토스테네스의 체가 있고, 두 번째는 어떤 수 n을 2부터 루트(n)까지 나눈 나머지가 모두 0이 아님을 확인하는 방법이 있습니다. 효율적인 소수 판별 알고리즘인 에라토스테네스의 체를 사용해서도 풀 수 있지만, 이 문제에서는 두 번째 방법으로도 충분히 해결할 수 있습니다.
//
//이 문제의 제한사항을 살펴보면 n이 1부터 1,000,000까지이고 k는 3부터 10까지이므로, 1,000,000을 3진수로 바꾸면 1,212,210,202,001입니다. 일반적으로 진법 변환은 문자열을 사용해 구현하므로, 진법 변환된 문자열을 0을 기준으로 파싱 한 후에 소수를 판별하는 과정에서 정수 자료형으로 변환이 필요하게 됩니다. 이때, 개발 언어에 따라서 int 자료형의 표현 범위를 벗어날 수 있음을 유의해서 문제를 풀어야 합니다. 예를 들어 997,244를 3진수로 바꾸면 1,212,122,221,222가 됩니다. 이 숫자는 0이 없기 때문에 진법 변환된 숫자 그대로 정수 자료형으로 변환해서 소수 판별을 해야 하는데, 이는 int 자료형의 표현 범위를 벗어난다는 것을 알 수 있습니다.

public class problem02 {
    public void exec() {
        solution();
    }

    private void solution() {
        System.out.println("hi");
    }
}