// initial state
const state = () => ({
  status: 'Home'
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
    state.status = menu;
  }
}

export default {
  namespaced: true,
  state,
  actions,
  mutations
}