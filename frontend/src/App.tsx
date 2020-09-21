import React, { useEffect, useRef, useState } from "react";
import axios from "axios";
import StompJS from "react-stomp";

function App() {
  useEffect(() => {
    axios.get("/api").then((res) => console.log(res.data));
  }, []);
  return (
    <>
      <StompJS
        ref={(client) => console.log(client)}
        url="http://localhost:8080/live"
        topics={["/topic/test-topic"]}
        onMessage={(message) => console.log(message)}
      />
    </>
  );
}

export default App;
