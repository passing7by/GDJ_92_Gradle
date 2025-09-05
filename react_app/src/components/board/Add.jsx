import React, { useEffect, useRef, useState } from "react";
import { createBrowserRouter, useNavigate } from "react-router-dom";

function Add() {
  
  // const [name, setName] = useState("test");
  
  // const n = useRef("아무거나");

  // function get() {
  //   console.log(add);
  // }
  
  // name에 들어가는 타입은 객체 혹은 배열이 될 수도 있음
  // const [add, setAdd] = useState({
  //   boardTitle: "",
  //   boardWriter: "",
  //   boardContents: ""
  // });

  // function inputChange(e) {
  //   setAdd((prevState) => ({
  //     ...prevState,
  //     [e.target.name]:e.target.value
  //   }))
  // }

  // add 버튼 클릭하면 ref 업데이트
  const [title, setTitle] = useState("")
  const [writer, setWriter] = useState("")
  const [contents, setContents] = useState("")

  const boardTitle = useRef("아무거나");
  const boardWriter = useRef("아무거나");
  const boardContents = useRef("아무거나");
  
  const navigate = useNavigate();

  const boardFiles = useRef([]);

  function insert() {

    let param = new URLSearchParams();
    param.append("boardTitle", boardTitle.current.value);
    param.append("boardWriter", boardWriter.current.value);
    param.append("boardContents", boardContents.current.value);
    
    // TODO 맞는지 검증
    console.log("boardFiles.current: " + boardFiles.current);
    param.append("attaches", boardFiles.current);

    // 로그인 유무 판단 혹은 권한이 필요할 때는 반드시 headers에 토큰을 넣어서 요청해야 함
    fetch('http://localhost/api/notice/add', {
      method: 'post',
      body: param,
      headers: {

      }
    })
    .then(r => r.json())
    .then(r => {
      console.log(r);
      
      if (r === true) {
        console.log("등록");
        navigate("/notice/list");
      }

      // setTitle(boardTitle.current.value);
      // setWriter(boardWriter.current.value);
      // setContents(boardContents.current.value);

      // boardTitle.current.value = "";
      // boardWriter.current.value = "";
      // boardContents.current.value = "";
    })
    ;
  }

  // function add(e) {
  //   e.preventDefault();
  //   const formData = new FormData(e.target); // <form></form> , e.target로 폼 객체 자체를 담아 줌
  // }

  let [files, setFiles] = useState([]);
  let fileIdx = useRef(0);

  function addFile() {
    if(files.length > 4) {
      alert('최대 5개까지 가능');

      return;
    } 

    const file = 
      <div key={fileIdx.current}>
        <input type="file" name="attaches" ref={boardFiles}></input>
        <button type="button" id={fileIdx.current} onClick={() => delFile(file.key)}>X</button>
      </div>;
    
    fileIdx.current = fileIdx.current + 1;
    console.log(fileIdx.current);

    const newFiles = [...files];
    newFiles.push(file);

    setFiles(newFiles);
  }

  function delFile(key) {
    console.log("key: " + key);

    setFiles((prev) => [...prev].filter(file => file.key != key)); // 상태 업데이트
  }

  return (
    <>
      <h1>Add Page</h1>
      {/* <h3>Input: {name}</h3> */}

      {/* ref={n}: input 태그 전체가 n과 연결됨 */}
      {/* <form onSubmit={add}> */}
        <input type="text" ref={boardTitle}/>
        <input type="text" ref={boardWriter} />
        <textarea ref={boardContents} id=""></textarea>
        {/* <input type="text" name="boardTitle" onChange={inputChange}/>
        <input type="text" name="boardWriter" onChange={inputChange} />
        <textarea name="boardContents" onChange={inputChange} id=""></textarea> */}

        <div>
          <button type="button" onClick={addFile}>Add File</button>
        </div>
        <div>
          {files}
        </div>


        {/* <button onClick={get}>click</button> */}
        <button onClick={insert}>insert</button>

        <h3>Add 내용</h3>
        <p>boardTitle: {title}</p>
        <p>boardWriter: {writer}</p>
        <p>boardContents: {contents}</p>
        {/* <button>Use Form</button> */}
      {/* </form> */}
    </>
  )
}

export default Add;