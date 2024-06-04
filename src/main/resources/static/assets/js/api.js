
// 서버 api에 호출을 수행하는 함수
export const callApi = async (url,method='GET',payload=null) => { 
    
    const requestInfo = {
        method,
    };
    if (payload) {
        requestInfo.headers = { 'content-type': 'application/json' };
        requestInfo.body = JSON.stringify(payload);
    }
    const res = await fetch(url, requestInfo);
    
    if (res.status === 403) {
        alert(`접근 권한이 없습니다.`);
        window.location.href = '/members/sign-in';
        return null;
    }
    return await res.json();
};