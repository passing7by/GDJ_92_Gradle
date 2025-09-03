import { useEffect, useState } from "react";

function List() {
  
  const [page, setPage] = useState(0);

  function nextPage() {
    setPage(page + 1);
  }

  const [boards, setBoards] = useState([]);
  
  const name = 'winter';

  const products = [
    { title: 'Cabbage', id: 1 },
    { title: 'Garlic', id: 2 },
    { title: 'Apple', id: 3 },
  ];

  // 반복문 돌릴 때 key라는 속성을 무조건 넣어야 함

  // const list = products.map(p =>
  //   <li key={p.id}> 
  //     {p.title}
  //   </li>
  // );
  
  // 이 코드는 위에 있는 코드와 같은 역할을 함
  const list = products.map(p => {
    return(
      <li key={p.id}> 
        {p.title}
      </li>
    )
  });

  // let boards = [];

  useEffect(() => {
    // 아래의 코드는 useEffect()안에서 실행되지 않으면 서버에 계속 요청을 보내게 됨
    // 이를 해결하기 위해 첫 렌더링, 그리고 page가 바뀔 때만 요청을 보내도록 useEffect() 안에서 작성
    fetch(`http://localhost/notice/list?page=${page}`)
      .then(r => r.json())
      .then(r => {
          // console.log(r);
          const b = r.content.map(v =>
            <li key={v.boardNum}>
              {v.boardTitle}
            </li>
          )
  
          setBoards(b);
      }) 
      ;
  }, [page]);
  
  return(
    <>
      <h1>List Page</h1>
      <h1>{name}</h1>
      <ul>
        {list}
      </ul>
      <ul>
        {boards}
      </ul>
      <div>
        <h3>Page: {page}</h3>
        <button onClick={nextPage}>NEXT</button>
      </div>
    </>
  )
}

export default List;