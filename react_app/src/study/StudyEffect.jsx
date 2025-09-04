import { useEffect, useState } from "react";

function StudyEffect() {

  const [count, setCount] = useState(0);

  function increase() {
    setCount(count + 1);
  }

  // 렌더링 할 때마다 실행 (mount, status 변경)
  useEffect(() => {
    console.log('Effect 1');
  });

  // 의존성 배열이 없다면: mount될 때만 (처음 렌더링 될 때만)
  // 의존성 배열이 있다면: mount될 때, 특정한 status 값이 바뀔 때
  useEffect(() => {
    console.log('Effect2');
  }, [count]); // 이 줄에서의 [] : 의존성 배열

  useEffect(() => {
    // clean up 코드
    // component가 unmount될 때 실행하고 싶은 코드
    return () => {
  
    }
  }

  )
  
  return(
    <>
      <h1>Use Effect</h1>
      <h1>{count}</h1>
      <button onClick={increase}>NEXT</button>
    </>
  )
}

export default StudyEffect;