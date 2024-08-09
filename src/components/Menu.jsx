import "./utils/menuCard.css";
const Menu = (props) => {
  const { menu, description, price } = props;

  return (
    <div className="menu-card">
      <h1>{menu}</h1>
      <p>{description}</p>
      <p>{`${price}$`}</p>
    </div>
  );
};

export default Menu;
