import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginForm from '@/components/LoginForm.vue'
import UserView from '../views/UserView.vue'
import ProductView from '../views/ProductView.vue'
import ProductList from '@/components/product/ProductList.vue'
import ProductRegist from '@/components/product/ProductRegist.vue'
import ProductSearch from '@/components/product/ProductSearch.vue'
import ProductDetail from '@/components/product/ProductDetail.vue'
import UserRegist from '@/components/user/UserRegist.vue'
import WishList from '@/components/wish/WishList.vue'
import PaymentView from '../views/PaymentView.vue'
import Payment from '@/components/payment/Payment.vue'
import PaymentDetail from '@/components/payment/PaymentDetail.vue'
import WishListDetail from '@/components/wish/WishListDetail.vue'
import AuthSuccessVue from '@/components/payment/AuthSuccess.vue'

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  {
    path: "/auth",
    component: UserView,
    children: [
      {
        path: "regist",
        name: "UserRegist",
        component: UserRegist,
        meta: { hideHeader: true }
      },
      {
        path: "login",
        name: "Login",
        component: LoginForm,
        meta: { hideHeader : true }
      },
    ],
  },
  {
    path: "/products",
    component: ProductView,
    children: [
      {
        path: "list",
        name: "ProductList",
        component: ProductList,
        props: (route) => ({ category: route.query.category }),
      },
      {
        path: "search",
        name: "ProductSearch",
        component: ProductSearch
      },
      {
        path: "save",
        name: "ProductRegist",
        component: ProductRegist
      },
      {
        path: "detail/:seq",
        name: "ProductDetail",
        component: ProductDetail
      },
    ]
  },
  {
    path: "/wish/list/:userSeq",
    name: "WishList",
    component: WishList
  },
  {
    path: "/wish/products/:seq",
    name: "WishListDetail",
    component: WishListDetail
  },  
  {
    path: "/pay",
    component: PaymentView,
    children: [
      {
        path: "/pay/:userSeq",
        name: "Payment",
        component: Payment
      },
      {
        path: "/pay/success",
        name: "authSuccess",
        component: AuthSuccessVue
      },
    ]
  },
  {
    path: "/pay/confirm/:userSeq",
    name: "PaymentDetail",
    component: PaymentDetail
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
