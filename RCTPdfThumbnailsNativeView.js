//  Created by react-native-create-bridge

import React, { Component } from 'react'
import { requireNativeComponent } from 'react-native'

const RCTPdfThumbnails = requireNativeComponent('RCTPdfThumbnails', RCTPdfThumbnailsView)

export default class RCTPdfThumbnailsView extends Component {
  render() {
    return <RCTPdfThumbnails {...this.props} />
  }
}
