// initial state
const state = () => ({
  menuActive: 'Home'
})
 
// actions
const actions = {
  setActive ({commit}, menu){
    commit('setMenuActive', menu);
  }
}
 
// mutations
const mutations = {
  // set active class for menu
  setMenuActive (state, menu){
    state.menuActive = menu;
  }
}
 
export default {
  namespaced: true,
  state,
  actions,
  mutations
}