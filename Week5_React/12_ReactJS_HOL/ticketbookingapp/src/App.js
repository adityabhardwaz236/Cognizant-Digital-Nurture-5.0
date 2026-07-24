import React, { Component } from "react";
import Greeting from "./Components/Greeting";
import LoginButton from "./Components/LoginButton";
import LogoutButton from "./Components/LogoutButton";

class App extends Component {
  constructor() {
    super();

    this.state = {
      isLoggedIn: false
    };
  }

  handleLogin = () => {
    this.setState({ isLoggedIn: true });
  };

  handleLogout = () => {
    this.setState({ isLoggedIn: false });
  };

  render() {
    return (
      <div style={{ margin: "100px" }}>
        <Greeting isLoggedIn={this.state.isLoggedIn} />

        {this.state.isLoggedIn ? (
          <LogoutButton onClick={this.handleLogout} />
        ) : (
          <LoginButton onClick={this.handleLogin} />
        )}
      </div>
    );
  }
}

export default App;