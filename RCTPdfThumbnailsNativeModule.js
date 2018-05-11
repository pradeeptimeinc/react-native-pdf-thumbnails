//  Created by react-native-create-bridge

import { NativeModules } from 'react-native'

const { RCTPdfThumbnails } = NativeModules

export default {
  exampleMethod () {
    return RCTPdfThumbnails.exampleMethod()
  },

  EXAMPLE_CONSTANT: RCTPdfThumbnails.EXAMPLE_CONSTANT
}
