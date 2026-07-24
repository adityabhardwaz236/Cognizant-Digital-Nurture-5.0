import officeImage from "./office.png";

function App() {
  const offices = [
    {
      name: "Sky Tower",
      rent: 55000,
      address: "Hyderabad"
    },
    {
      name: "Tech Park",
      rent: 75000,
      address: "Bangalore"
    },
    {
      name: "Cyber Hub",
      rent: 45000,
      address: "Chennai"
    }
  ];
  return (
    <div className="App" style={{
      marginLeft: "180px",
      marginTop: "30px"
    }}>
      <h1>Office Space Rental App</h1>

      <img src={officeImage} alt="Office" width={500} height={200} />
      {
        offices.map((office) => (
          <div style={{
            marginLeft: 5
          }}>
            <h1>Name: {office.name}</h1>
            <h2 style={{ color: office.rent < 60000 ? "red" : "green" }}>Rent: {office.rent}</h2>
            <h3>Address: {office.address}</h3>
            <br></br>
          </div>
        ))
      }
    </div>
  );
}

export default App;