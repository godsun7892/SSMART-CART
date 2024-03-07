import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { 
      // 첫 화면
      path: '/',
      name: 'start',
      component: () => import('../views/FirstScreen.vue')
    },
    { 
      // 카트 사용 전 대기화면
      path: '/home',
      name: 'home',
      component: () => import('../views/HomeView2.vue')
    },
    {
      // 휴대폰 번호 입력 화면
      path: '/phone',
      name: 'phone',
      component: () => import('../views/Phone.vue')
    },
    {
      // 지문 인식을 요청하는 화면
      path: '/fingerPrint',
      name: 'fingerPrint',
      component: () => import('../views/fingerLogin.vue')
    },
    {
      // 지문 등록을 요청하는 화면
      path: '/fingerEnroll',
      name: 'fingerEnroll',
      component: () => import('../views/fingerEnroll.vue')
    },
    {
      // PW를 입력하는 화면
      path: '/loginMain',
      name: 'loginMain',
      component: () => import('../views/loginMain2.vue')
    },
    {
      // 지문 등록 시 PW를 입력하는 화면
      path: '/loginProcess',
      name: 'loginProcess',
      component: () => import('../views/loginProcess.vue')
    },
    {
      // 지문을 등록할 것인가를 선택하는 화면
      path: '/fingerPrintRequest',
      name: 'fingerPrintRequest',
      component: () => import('../views/fingerPrint/Request.vue')
    },
    {
      // 지문을 등록하는 화면
      path: '/login_fp',
      name: 'login_fp',
      component: () => import('../views/fingerPrint/fp_enroll_login.vue')
    },
    {
      // 지문 등록 또는 쇼핑 선택하는 화면
      path: '/select',
      name: 'select',
      component: () => import('../views/EnrollSelect.vue')
    },
    {
      path: '/shoppingBasket',
      name: 'shoppingBasket',
      component: () => import('../views/shop_ver4.vue')
    },
    {
      path: '/payMent',
      name: 'payMent',
      component: () => import('../views/payment.vue')
    },
    {
      path: '/paymentProgress',
      name: 'paymentProgress',
      component: () => import('../views/paymentProgress.vue')
    },
    {
      path: '/paymentSuccess',
      name: 'paymentSuccess',
      component: () => import('../views/paymentSuccess.vue')
    },
    {
      path: '/shoppingEnd',
      name: 'shoppingEnd',
      component: () => import('../views/shopping_End.vue')
    },
    {
      path: '/logo',
      name: 'logo',
      component: () => import('../views/Loading.vue')
    }
  ]
})

export default router