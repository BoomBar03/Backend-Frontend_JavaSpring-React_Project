import Menu from "./Menu";

const Results = ({ menus }) => {
  return (
    <div className="search">
      {!menus.length ? (
        <h1>No menus found</h1>
      ) : (
        menus.map((menu) => {
          return (
            <Menu
              menu={menu.menu}
              key={menu.id}
              description={menu.description}
              price={menu.price}
              id={menu.id}
            />
          );
        })
      )}
    </div>
  );
};

export default Results;
