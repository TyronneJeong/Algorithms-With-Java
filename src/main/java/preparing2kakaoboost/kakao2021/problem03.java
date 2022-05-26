package preparing2kakaoboost.kakao2021;

//문제 3 – 순위 검색
//정답률 : 정확성 44.07%, 효율성 4.49%
//문제 3 풀러 가기
//
//
//카카오는 하반기 경력 개발자 공개채용을 진행 중에 있으며 현재 지원서 접수와 코딩 테스트가 종료되었습니다. 이번 채용에서 지원자는 지원서 작성 시 아래와 같이 4가지 항목을 반드시 선택하도록 하였습니다.
//
//코딩 테스트 참여 개발 언어 항목에 cpp, java, python 중 하나를 선택해야 합니다.
//지원 직군 항목에 backend와 frontend 중 하나를 선택해야 합니다.
//지원 경력 구분 항목에 junior와 senior 중 하나를 선택해야 합니다.
//선호하는 소울푸드로 chicken과 pizza 중 하나를 선택해야 합니다.
//
//
//인재영입팀에 근무하고 있는 니니즈는 코딩 테스트 결과를 분석하여 채용에 참여한 개발팀들에 제공하기 위해 지원자들의 지원 조건을 선택하면 해당 조건에 맞는 지원자가 몇 명인 지 쉽게 알 수 있는 도구를 만들고 있습니다.
//예를 들어, 개발팀에서 궁금해하는 문의사항은 다음과 같은 형태가 될 수 있습니다.
//코딩 테스트에 java로 참여했으며, backend 직군을 선택했고, junior 경력이면서, 소울푸드로 pizza를 선택한 사람 중 코딩 테스트 점수를 50점 이상 받은 지원자는 몇 명인가?
//
//물론 이 외에도 각 개발팀의 상황에 따라 아래와 같이 다양한 형태의 문의가 있을 수 있습니다.
//
//코딩 테스트에 python으로 참여했으며, frontend 직군을 선택했고, senior 경력이면서, 소울푸드로 chicken을 선택한 사람 중 코딩 테스트 점수를 100점 이상 받은 사람은 모두 몇 명인가?
//코딩 테스트에 cpp로 참여했으며, senior 경력이면서, 소울푸드로 pizza를 선택한 사람 중 코딩 테스트 점수를 100점 이상 받은 사람은 모두 몇 명인가?
//backend 직군을 선택했고, senior 경력이면서 코딩 테스트 점수를 200점 이상 받은 사람은 모두 몇 명인가?
//소울푸드로 chicken을 선택한 사람 중 코딩 테스트 점수를 250점 이상 받은 사람은 모두 몇 명인가?
//코딩 테스트 점수를 150점 이상 받은 사람은 모두 몇 명인가?
//
//
//즉, 개발팀에서 궁금해하는 내용은 다음과 같은 형태를 갖습니다.
//
//* [조건]을 만족하는 사람 중 코딩 테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가?
//
//문제
//지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩 테스트 점수를 하나의 문자열로 구성한 값의 배열 info, 개발팀이 궁금해하는 문의 조건이 문자열 형태로 담긴 배열 query가 매개변수로 주어질 때,
//각 문의 조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
//
//
//제한사항
//
//info 배열의 크기는 1 이상 50,000 이하입니다.
//info 배열 각 원소의 값은 지원자가 지원서에 입력한 4가지 값과 코딩 테스트 점수를 합친 “개발 언어 직군 경력 소울푸드 점수” 형식입니다.
//개발 언어는 cpp, java, python 중 하나입니다.
//직군은 backend, frontend 중 하나입니다.
//경력은 junior, senior 중 하나입니다.
//소울푸드는 chicken, pizza 중 하나입니다.
//점수는 코딩 테스트 점수를 의미하며, 1 이상 100,000 이하인 자연수입니다.
//각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
//query 배열의 크기는 1 이상 100,000 이하입니다.
//query의 각 문자열은 “[조건] X” 형식입니다.
//[조건]은 “개발 언어 and 직군 and 경력 and 소울푸드” 형식의 문자열입니다.
//언어는 cpp, java, python, – 중 하나입니다.
//직군은 backend, frontend, – 중 하나입니다.
//경력은 junior, senior, – 중 하나입니다.
//소울푸드는 chicken, pizza, – 중 하나입니다.
//‘-‘ 표시는 해당 조건을 고려하지 않겠다는 의미입니다.
//X는 코딩 테스트 점수를 의미하며 조건을 만족하는 사람 중 X점 이상 받은 사람은 모두 몇 명인 지를 의미합니다.
//각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
//예를 들면, “cpp and – and senior and pizza 500″은 “cpp로 코딩 테스트를 봤으며, 경력은 senior 이면서 소울푸드로 pizza를 선택한 지원자 중 코딩 테스트 점수를 500점 이상 받은 사람은 모두 몇 명인가?”를 의미합니다.
//
//[입출력 예]
//
//info	query	result
//["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"]	["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]	[1,1,1,1,2,4]
//
//입출력 예에 대한 설명
//
//지원자 정보를 표로 나타내면 다음과 같습니다.
//
//언어	직군	경력	소울 푸드	점수
//java	backend	junior	pizza	150
//python	frontend	senior	chicken	210
//python	frontend	senior	chicken	150
//cpp	backend	senior	pizza	260
//java	backend	junior	chicken	80
//python	backend	senior	chicken	50
//"java and backend and junior and pizza 100" : java로 코딩 테스트를 봤으며, backend 직군을 선택했고 junior 경력이면서 소울푸드로 pizza를 선택한 지원자 중 코딩 테스트 점수를 100점 이상 받은 지원자는 1명 입니다.
//"python and frontend and senior and chicken 200" : python으로 코딩 테스트를 봤으며, frontend 직군을 선택했고, senior 경력이면서 소울 푸드로 chicken을 선택한 지원자 중 코딩 테스트 점수를 200점 이상 받은 지원자는 1명 입니다.
//"cpp and - and senior and pizza 250" : cpp로 코딩 테스트를 봤으며, senior 경력이면서 소울푸드로 pizza를 선택한 지원자 중 코딩 테스트 점수를 250점 이상 받은 지원자는 1명 입니다.
//"- and backend and senior and - 150" : backend 직군을 선택했고, senior 경력인 지원자 중 코딩 테스트 점수를 150점 이상 받은 지원자는 1명 입니다.
//"- and - and - and chicken 100" : 소울푸드로 chicken을 선택한 지원자 중 코딩 테스트 점수를 100점 이상을 받은 지원자는 2명 입니다.
//"- and - and - and - 150" : 코딩 테스트 점수를 150점 이상 받은 지원자는 4명 입니다.
//
//문제 풀이
//
//본 문제는 정확성 테스트와 효율성 테스트 두 가지 방식으로 검증하는 문제입니다. 효율성 테스트의 경우 주어진 시간 내에 실행이 완료되어야 하므로, 최적화된 구현 방법을 고민할 필요가 있습니다.
//
//우선, 매 문의 조건마다 INFO 배열에서 조건에 해당하는 지원자들을 찾으면서 X점 이상 받은 사람은 몇 명인지 구한다면 정확성 테스트를 통과할 수 있습니다.
//그러나 효율성 테스트의 경우에는 위와 같은 방식으로 매번 지원자들을 찾는다면 통과할 수 없습니다. 문제 해결을 위해서, 지원자들을 그룹별로 적절하게 미리 분류해두면 매 문의 조건마다 지원자들을 INFO 배열에서 찾지 않아도 됩니다.
//
//예를 들어, “java backend junior pizza 150” 지원자의 경우 다음과 같이 16가지 그룹에 속하게 됩니다.
//
//언어	직군	경력	소울 푸드	점수
//java	backend	junior	pizza	150
//–	backend	junior	pizza	150
//java	–	junior	pizza	150
//java	backend	–	pizza	150
//java	backend	junior	–	150
//–	–	junior	pizza	150
//–	backend	–	pizza	150
//… (생략)
//java	–	–	–	150
//–	–	–	–	150
//검색 조건이 “java and backend and junior and pizza 100″이라 하면, 위 지원자는 검색 대상에 포함되며, 검색 조건이 “java and – and junior and – 100” 인 경우에도 검색 대상에 포함이 됩니다.
//
//따라서 모든 지원자들을 위와 같은 방식으로 분류한 후 같은 그룹의 지원자끼리 묶어두고, 해당 그룹에서 점수를 기준으로 오름차순 정렬해 둡니다.
//
//이제, 검색 조건이 주어질 경우 INFO 배열에서 지원자들을 찾지 않고, 미리 분류해둔 그룹에서 X점 이상 맞은 지원자 수를 세주기만 하면 됩니다.
//
//이때, X점 이상 맞은 지원자를 찾기 위해 해당 그룹의 최저점, 혹은 최고점부터 순차적으로 검색한다면 여전히 오랜 시간이 걸리게 됩니다. 이때, 숫자가 오름차순으로 정렬된 배열에서 X라는 숫자를 찾는 효율적인 방법으로 binary search를 사용할 수 있습니다. 이때, 배열에 X가 없을 수도 있으므로, 배열에서 X보다 크거나 같은 숫자가 처음 나타나는 위치를 찾아야 하며, 이는 lower bound를 이용하면 됩니다.

public class problem03 {
    public void exec() {
        solution();
    }

    private void solution() {
        System.out.println("hi");
    }
}
