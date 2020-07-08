import api from "@/plugin/common-code-api";
export function updateCode(cdId, dtlCdId, data, callback) {
  api.putRequest(`/common-codes/${cdId}/${dtlCdId}`, data).then(callback());
}
