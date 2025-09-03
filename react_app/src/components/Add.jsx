import { useRef, useState } from "react";

function Add() {
  
  const [name, setName] = useState("test");

  const n = useRef("아무거나");

  function get() {
    console.log(n.current.value);
    n.current.focus();
    setName(n.current.value);
  }

  // add 버튼 클릭하면 ref 업데이트
  const [title, setTitle] = useState("")
  const [contents, setContents] = useState("")

  const boardTitle = useRef("아무거나");
  const boardContents = useRef("아무거나");
  
  function add() {
    let param = new URLSearchParams();
    param.append("boardTitle", boardTitle.current.value);
    param.append("boardContents", boardContents.current.value);

    fetch('http://localhost/notice/add', {
      method: 'post',
      body: param
    })
    .then(r => r.json())
    .then(r => {
      console.log(r);

      setTitle(boardTitle.current.value);
      setContents(boardContents.current.value);

      boardTitle.current.value = "";
      boardContents.current.value = "";
    })
    ;
  }

  return (
    <>
      <h1>Add Page</h1>
      <h3>Input: {name}</h3>

      {/* ref={n}: input 태그 전체가 n과 연결됨 */}
      <input type="text" ref={n}/>
      <input type="text" ref={boardTitle} />
      <textarea ref={boardContents} id=""></textarea>

      <button onClick={get}>click</button>
      <button onClick={add}>add</button>

      <h3>Add 내용</h3>
      <p>boardTitle: {title}</p>
      <p>boardContents: {contents}</p>
    </>
  )
}

export default Add;