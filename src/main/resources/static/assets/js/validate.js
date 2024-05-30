

// 서버에 중복확인 비동기 요청
export const checkAvailability = async (type, keyword) => {
  const response = await fetch(`http://localhost:8383/members/check?type=${type}&keyword=${keyword}`);
  const flag = await response.json();
  return !flag;
};


// 유효성 검증에 사용될 정규표현식 패턴들 정의

// 아이디 패턴: 영문 대소문자와 숫자, 4~14글자
const accountPattern = /^[a-zA-Z0-9]{4,14}$/;

// 비밀번호 패턴: 반드시 영문, 숫자, 특수문자를 포함하여 8자 이상
const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*?_~])[A-Za-z\d!@#$%^&*?_~]{8,}$/;

// 이름 패턴: 한글만 허용
const namePattern = /^[가-힣]+$/;

// 이메일 패턴: 기본적인 이메일 형식
const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;


export const validateInput = {
  // 아이디 유효성 검사 함수
  account: async (value) => {
    // 빈 값 검사
    if (!value.trim()) return { valid: false, message: '아이디는 필수값입니다!' };
    // 정규표현식 검사
    if (!accountPattern.test(value)) return { valid: false, message: '아이디는 4~14글자의 영문,숫자로 입력하세요.' };
    // 중복 검사
    const isAvailable = await checkAvailability('account', value);
    // 중복 여부에 따라 결과 반환
    return isAvailable ? { valid: true } : { valid: false, message: '아이디가 중복되었습니다.' };
  },
  // 비밀번호 유효성 검사 함수
  password: (value) => {
    // 빈 값 검사
    if (!value.trim()) return { valid: false, message: '비밀번호는 필수값입니다!' };
    // 정규표현식 검사
    if (!passwordPattern.test(value)) return { valid: false, message: '비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다!' };
    // 유효한 경우
    return { valid: true };
  },
  // 비밀번호 확인 유효성 검사 함수
  passwordCheck: (value, password) => {
    // 빈 값 검사
    if (!value.trim()) return { valid: false, message: '비밀번호 확인란은 필수값입니다!' };
    // 비밀번호 일치 여부 검사
    if (value !== password) return { valid: false, message: '비밀번호가 일치하지 않습니다!' };
    // 유효한 경우
    return { valid: true };
  },
  // 이름 유효성 검사 함수
  name: (value) => {
    // 빈 값 검사
    if (!value.trim()) return { valid: false, message: '이름은 필수정보입니다!' };
    // 정규표현식 검사
    if (!namePattern.test(value)) return { valid: false, message: '이름은 한글로 입력해주세요.' };
    // 유효한 경우
    return { valid: true };
  },
  // 이메일 유효성 검사 함수
  email: async (value) => {
    // 빈 값 검사
    if (!value.trim()) return { valid: false, message: '이메일은 필수값입니다!' };
    // 정규표현식 검사
    if (!emailPattern.test(value)) return { valid: false, message: '이메일 형식을 지켜주세요.' };
    // 중복 검사
    const isAvailable = await checkAvailability('email', value);
    // 중복 여부에 따라 결과 반환
    return isAvailable ? { valid: true } : { valid: false, message: '이메일이 중복되었습니다.' };
  }
};
