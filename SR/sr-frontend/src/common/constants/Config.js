//mapping menu and page title
const homePage = "Home";
const adminPage = "Admin";
const codePage = "Code";
const userPage = "User";
const listPage = "List";
const myListPage = "My List";
const detailPage = "Detail";
const logoutPage = "Logout";
const loginPage = "Login";
const errorPage = "Error";
export const MenuList = [
  homePage,
  adminPage,
  codePage,
  userPage,
  listPage,
  myListPage,
  detailPage
];

export const MenuDetail = {
  [homePage]: {
    title: "Home",
    icon: "",
    url: "",
    component: "Home",
    menu: [1]
  },
  [adminPage]: {
    title: "User Management",
    icon: "Icons-01.png",
    url: "admin",
    component: "UserManagement",
    menu: [1]
  },
  [codePage]: {
    title: "Common Code Management",
    icon: "Icons-02.png",
    url: "code",
    component: "CommonCodeManagement",
    menu: [1]
  },
  [userPage]: {
    title: "User Search",
    icon: "Icons-03.png",
    url: "user",
    component: "UserSearch",
    menu: [1]
  },
  [listPage]: {
    title: "SR List",
    icon: "Icons-04.png",
    url: "sr-list",
    component: "SRList",
    menu: [1]
  },
  [myListPage]: {
    title: "My List",
    icon: "Icons-05.png",
    url: "my-list",
    component: "MyList",
    menu: [1]
  },
  [detailPage]: {
    title: "SR Detail",
    icon: "",
    url: "detail",
    component: "SRDetail",
    menu: [1]
  },
  [logoutPage]: {
    title: "Log Out",
    icon: "Icons-06.png",
    url: "logout",
    component: "",
    menu: [0]
  },
  [loginPage]: {
    title: "Log Out",
    icon: "Icons-06.png",
    url: "login",
    component: "",
    menu: [0]
  },
  [errorPage]: {
    title: "",
    icon: "",
    url: "error",
    component: "Error",
    menu: [0]
  }
};

export const MenuTitleMapping = {
  [homePage]: "",
  [adminPage]: "User Management",
  [codePage]: "Common Code Management",
  [userPage]: "User Search",
  [listPage]: "SR List",
  [myListPage]: "My List",
  [detailPage]: "SR Detail",
  [logoutPage]: "Log Out"
};

export const HomeIconMapping = {
  [adminPage]: "Icons-01.png",
  [codePage]: "Icons-02.png",
  [userPage]: "Icons-03.png",
  [listPage]: "Icons-04.png",
  [myListPage]: "Icons-05.png",
  [logoutPage]: "Icons-06.png"
};

export const MenuUrlMapping = {
  [homePage]: "",
  [adminPage]: "admin",
  [codePage]: "code",
  [userPage]: "user",
  [listPage]: "sr-list",
  [myListPage]: "my-list",
  [detailPage]: "detail",
  [logoutPage]: "logout",
  [errorPage]: "error"
};

export const MenuComponentMapping = {
  [homePage]: "Home",
  [adminPage]: "UserManagement",
  [codePage]: "CommonCodeManagement",
  [userPage]: "UserSearch",
  [listPage]: "SRList",
  [myListPage]: "MyList",
  [detailPage]: "SRDetail"
};

export const bgColor = "#034EA2";

export const UpdateMessage = "This code id is exists! Are you want to update?";
export const InsertMessage =
  "This code id is not exists! Are you want to create?";
export const ErrorMessage = "Oops! Something is wrong.";
export const SuccessMessage = "Process Successful.";
