import React, { Component } from "react";

class CurrencyConverter extends Component {
  constructor() {
    super();

    this.state = {
      amount: "",
    };
  }

  handleChange = (event) => {
    this.setState({
      amount: event.target.value,
    });
  };

  handleSubmit = () => {
    const euro = this.state.amount * 80;

    alert("Converting to Euro Amount is " + euro);
  };

  render() {
    return (
      <div>
        <h1 style={{ color: "green" }}>
          Currency Convertor!
        </h1>

        <table>
          <tbody>
            <tr>
              <td>Amount:</td>
              <td>
                <input
                  type="number"
                  value={this.state.amount}
                  onChange={this.handleChange}
                />
              </td>
            </tr>

            <tr>
              <td>Currency:</td>
              <td>
                <input
                  type="text"
                  value="Euro"
                  readOnly
                />
              </td>
            </tr>

            <tr>
              <td></td>
              <td>
                <br />
                <button onClick={this.handleSubmit}>
                  Submit
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    );
  }
}

export default CurrencyConverter;