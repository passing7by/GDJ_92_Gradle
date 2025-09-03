import { useRef, useState } from "react";

function StudyRef() {
  
  const [count, setCount] = useState(0);

  // useRef() : 값을 담을 수는 있으나, 렌더링이 일어나지는 않음
  const age = useRef(0);

  function increase() {
    setCount(count + 1);
  }

  function next() {
    age.current = 3;
    console.log(age.current);
  }

  return (
    <>
      <h1>Ref</h1>
      <h1>{count}</h1>
      <h1>{age.current}</h1>
      <button onClick={increase}>click</button>
      <button onClick={next}>Ref</button>
    </>
  )
}

export default StudyRef;