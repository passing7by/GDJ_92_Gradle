import { useState } from 'react';

function StudyState() {  
  // let count = 0;
  const [count, setCount] = useState(1); // useState()의 매개변수에는 초기값을 넣어 줌

  function increse() {
    // count++;
    setCount(count + 1);
    console.log(count);
  }

  return(
    <>
      <h1>Study State</h1>
      <h1>{count}</h1>
      <button onClick={increse}>CLICK</button>
    </>
  )
}

export default StudyState;