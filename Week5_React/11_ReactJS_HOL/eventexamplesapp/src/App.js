import React, { Component } from "react";
import CurrencyConverter from "./Components/CurrencyConverter";

class App extends Component {
  constructor() {
    super();

    this.state = {
      count: 0,
    };
  }

  increment = () => {
    this.setState((prevState) => ({
      count: prevState.count + 1,
    }));
  };

  decrement = () => {
    this.setState((prevState) => ({
      count: prevState.count - 1,
    }));
  };

  sayHello = () => {
    alert("Hello! Member");
  };

  increase = () => {
    this.increment();
    this.sayHello();
  };

  sayWelcome = (msg) => {
    alert(msg);
  };

  onPress = () => {
    alert("I was clicked");
  };

  render() {
    return (
      <div style={{ margin: "20px" }}>
        <h2>Counter : {this.state.count}</h2>

        <button onClick={this.increase}>Increment</button>

        {/* <br />
        <br /> */}

        <button style={{marginLeft:5}} onClick={this.decrement}>Decrement</button>

        <br />
        <br />

        <button onClick={() => this.sayWelcome("Welcome")}>
          Say welcome
        </button>

        <br />
        <br />

        <button onClick={this.onPress}>
          Click on me
        </button>

        <hr />

        <CurrencyConverter />
      </div>
    );
  }
}

export default App;